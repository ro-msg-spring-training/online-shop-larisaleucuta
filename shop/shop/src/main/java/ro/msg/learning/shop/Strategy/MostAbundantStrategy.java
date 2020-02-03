package ro.msg.learning.shop.Strategy;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.Configuration.LocationStrategy;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Entities.Location;
import ro.msg.learning.shop.Entities.OrderDetail;
import ro.msg.learning.shop.Entities.Stock;
import ro.msg.learning.shop.Exceptions.ProductsNotOnStockException;
import ro.msg.learning.shop.Mapper.LocationMapper;
import ro.msg.learning.shop.Repositories.LocationRepository;
import ro.msg.learning.shop.Repositories.StockRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
public class MostAbundantStrategy implements LocationStrategy {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;

    List<StockDto> stock = new ArrayList<>();


    @Override
    public List<StockDto> doStrategy(List<OrderDetailDto> productList) {

        List<Location> locationList = locationRepository.findAll();
        for (Location location : locationList) {
            List<Stock> stockList = stockRepository.findAllByLocation(location);

            for (OrderDetailDto orderDetailDto : productList) {
                Optional<Stock> maxStock = stockList.stream().max(Comparator.comparing(Stock::getQuantity));
                if (maxStock.isPresent()) {
                    Stock myStock = maxStock.get();
                    if (myStock.getQuantity() < orderDetailDto.getQuantity())
                        throw new ProductsNotOnStockException();

                    StockDto element = StockDto.builder()
                            .locationDto(locationMapper.mapLocationToLocationDto(myStock.getLocation()))
                            .quantity(orderDetailDto.getQuantity())
                            .productId(myStock.getProduct().getProductId())
                            .build();
                    stock.add(element);
                }
            }

        }
        return stock;
    }
}
