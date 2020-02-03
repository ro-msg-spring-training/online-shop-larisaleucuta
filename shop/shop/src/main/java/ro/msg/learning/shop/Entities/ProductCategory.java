package ro.msg.learning.shop.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
public class ProductCategory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer category_id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<Product>();
}
