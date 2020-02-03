package ro.msg.learning.shop.Entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer productId;

    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    private String imageUrl;

    @ManyToOne
    private ProductCategory category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Stock> stockList = new ArrayList<>();
}
