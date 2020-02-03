package ro.msg.learning.shop.Mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Entities.Stock;

@Component
@RequiredArgsConstructor
public class StockMapper {

    private final LocationMapper locationMapper;

    public StockDto mapStockToStockDto(Stock stock){
        return StockDto.builder()
                .locationDto(locationMapper.mapLocationToLocationDto(stock.getLocation()))
                .productId(stock.getProduct().getProductId())
                .quantity(stock.getQuantity())
                .build();
    }
}
