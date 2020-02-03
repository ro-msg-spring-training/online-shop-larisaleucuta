package ro.msg.learning.shop.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Stock {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer stock_id;

    @ManyToOne()
    private Product product;

    @ManyToOne
    private Location location;

    private int quantity;


}
