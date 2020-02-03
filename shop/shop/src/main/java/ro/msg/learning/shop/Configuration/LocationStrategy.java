package ro.msg.learning.shop.Configuration;

import ro.msg.learning.shop.DTO.OrderDetailDto;
import ro.msg.learning.shop.DTO.StockDto;
import ro.msg.learning.shop.Entities.OrderDetail;

import java.util.List;

public interface LocationStrategy {
    List<StockDto> doStrategy(List<OrderDetailDto> productList);
}
