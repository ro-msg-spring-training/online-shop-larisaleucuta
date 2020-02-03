package ro.msg.learning.shop.Strategy;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.Configuration.LocationStrategy;
import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Entities.Location;
import ro.msg.learning.shop.Entities.OrderDetail;
import ro.msg.learning.shop.Entities.Stock;
import ro.msg.learning.shop.Exceptions.ProductNotFoundException;
import ro.msg.learning.shop.Exceptions.ProductsNotOnStockException;
import ro.msg.learning.shop.Mapper.LocationMapper;
import ro.msg.learning.shop.Repositories.LocationRepository;
import ro.msg.learning.shop.Repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;

@Data
public class SingleLocationStrategy implements LocationStrategy {

    @Autowired
    private LocationMapper locationMapper;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private StockRepository stockRepository;

    List<StockDto> requiredStock = new ArrayList<>();


    @Override
    public List<StockDto> doStrategy(List<OrderDetailDto> productList) {

        List<Location> locationList = locationRepository.findAll();
        for (Location location : locationList) {
            List<Stock> stockList = stockRepository.findAllByLocation(location);
            requiredStock.clear();
            for (OrderDetailDto product : productList) {
                stockList.forEach(stock -> {
                    if (stock.getProduct().getProductId().equals(product.getProductId()) && stock.getQuantity() > product.getQuantity()) {
                        requiredStock.add(
                                StockDto.builder()
                                        .locationDto(locationMapper.mapLocationToLocationDto(stock.getLocation()))
                                        .productId(stock.getProduct().getProductId())
                                        .quantity(stock.getQuantity())
                                        .build());
                    }else throw new ProductsNotOnStockException();
                });
            }
        }
        return requiredStock;
    }

}

