package ro.msg.learning.shop.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Entities.Stock;
import ro.msg.learning.shop.Exceptions.ProductsNotOnStockException;
import ro.msg.learning.shop.Mapper.LocationMapper;
import ro.msg.learning.shop.Mapper.StockMapper;
import ro.msg.learning.shop.Repositories.LocationRepository;
import ro.msg.learning.shop.Repositories.ProductRepository;
import ro.msg.learning.shop.Repositories.StockRepository;

import javax.validation.constraints.Null;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockMapper stockMapper;
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    public StockDto createStock(StockDto stockDto) {
        if (productRepository.findProductByProductId(stockDto.getProductId())!= null) {
            Stock stock = Stock.builder()
                    .location(locationMapper.mapLocationDtoToLocation(stockDto.getLocationDto()))
                    .product(productRepository.findProductByProductId(stockDto.getProductId()))
                    .quantity(stockDto.getQuantity())
                    .build();
            stockRepository.save(stock);
            return stockMapper.mapStockToStockDto(stock);
        }
        else {
            throw new ProductsNotOnStockException();
        }
    }

    public StockDto updateStock(StockDto stockDto){
        Stock stock = stockRepository.findByLocationAndProduct(locationMapper.mapLocationDtoToLocation(stockDto.getLocationDto()), productRepository.findProductByProductId(stockDto.getProductId()));
        stock.setQuantity(stock.getQuantity() -stockDto.getQuantity());
        stockRepository.save(stock);
        return stockMapper.mapStockToStockDto(stock);
    }

}
