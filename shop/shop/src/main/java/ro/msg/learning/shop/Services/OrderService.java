package ro.msg.learning.shop.Services;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Configuration.LocationStrategy;
import ro.msg.learning.shop.DTO.OrderDto;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Mapper.AddressMapper;
import ro.msg.learning.shop.Mapper.OrderMapper;
import ro.msg.learning.shop.Entities.Address;
import ro.msg.learning.shop.Entities.Location;
import ro.msg.learning.shop.Entities.Orders;
import ro.msg.learning.shop.Repositories.AddressRepository;
import ro.msg.learning.shop.Repositories.LocationRepository;
import ro.msg.learning.shop.Repositories.OrderRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final LocationRepository locationRepository;
    private final LocationStrategy locationStrategy;
    private final StockService stockService;


    public Address testAddress(String streetAddress,String city, String country, String county){
        Address address = null;
        Optional<Address> addressOptional = addressRepository.findByStreetAddressAndCityAndCountryAndCounty(streetAddress,city,country,county);
        if(addressOptional.isPresent()){
            address = addressOptional.get();
        }else{
            address = Address.builder()
                    .streetAddress(streetAddress)
                    .city(city)
                    .country(country)
                    .county(county)
                    .build();
            addressRepository.save(address);
        }
        return address;
    }

    public Location testLocation(String name, Address address){
        Location location=null;
        Optional<Location> locationOptional = locationRepository.findByName(name);
        if(locationOptional.isPresent()){
            location=locationOptional.get();
        }else{
            location = Location.builder()
                    .name(name)
                    .locationAddress(this.testAddress(address.getStreetAddress(),address.getCity(),address.getCountry(),address.getCounty()))
                    .build();
            locationRepository.save(location);
        }
        return location;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        List<StockDto> ordered = locationStrategy.doStrategy(orderDto.getOrderDetailDtoList());

        Address deliveryAddress = Address.builder()
                .streetAddress(orderDto.getDeliveryAddress().getStreetAddress())
                .city(orderDto.getDeliveryAddress().getCity())
                .country(orderDto.getDeliveryAddress().getCountry())
                .county(orderDto.getDeliveryAddress().getCounty())
                .build();

        Location location = Location.builder()
                .locationAddress(deliveryAddress)
                .name("Fgd")
                .build();

        Orders orders = Orders.builder()
                .createdAt(LocalDateTime.now())
                .deliveryAddress(this.testLocation(location.getName(),deliveryAddress))
                .orderDetailList(orderMapper.mapOrderDetailDtoListToOrderDetailList(orderDto.getOrderDetailDtoList()))
                .build();

        orders.getOrderDetailList().forEach(orderDetail -> orderDetail.setOrders(orders));
        orderRepository.save(orders);

        ordered.forEach(stockService::updateStock);

        return orderMapper.mapOrderToOrderDto(orders);
    }
}

