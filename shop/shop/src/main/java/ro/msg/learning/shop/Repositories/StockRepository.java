package ro.msg.learning.shop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.Entities.Location;
import ro.msg.learning.shop.Entities.Product;
import ro.msg.learning.shop.Entities.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    public List<Stock> findAllByLocation (Location location);
    public Stock findByLocationAndProduct(Location location, Product product);
}
