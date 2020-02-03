package ro.msg.learning.shop.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.DTO.OrderDto;
import ro.msg.learning.shop.Entities.Orders;
import ro.msg.learning.shop.Entities.OrderDetail;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final AddressMapper addressMapper;

    public List<OrderDetailDto> mapOrderDetailListToOrderDetailDtoList(List<OrderDetail> orderDetailList){
        return orderDetailList.stream().map(this::mapOrderDetailToOrderDetailDto).collect(Collectors.toList());
    }

    public List<OrderDetail> mapOrderDetailDtoListToOrderDetailList(List<OrderDetailDto> orderDetailListDto) {
        return orderDetailListDto.stream().map(this::mapOrderDetailDtoToOrderDetail).collect(Collectors.toList());
    }

    public OrderDetailDto mapOrderDetailToOrderDetailDto(OrderDetail orderDetail){
        return OrderDetailDto.builder()
                .productId(orderDetail.getProductId())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public OrderDetail mapOrderDetailDtoToOrderDetail(OrderDetailDto orderDetailDto){
        return OrderDetail.builder()
                .productId(orderDetailDto.getProductId())
                .quantity(orderDetailDto.getQuantity())
                .build();
    }

    public OrderDto mapOrderToOrderDto(Orders orders){
        return OrderDto.builder()
                .timeStamp(orders.getCreatedAt())
                .deliveryAddress(addressMapper.mapAddressToAddressDto(orders.getDeliveryAddress().getLocationAddress()))
                .orderDetailDtoList(mapOrderDetailListToOrderDetailDtoList(orders.getOrderDetailList()))
                .build();
    }

}
