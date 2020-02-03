package ro.msg.learning.shop.Entities;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Orders {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer order_id;

    @ManyToOne
    private Location shippedFrom;

    @ManyToOne
    private User customer;

    private LocalDateTime createdAt;

    @OneToOne
    private Location deliveryAddress;

    @OneToMany(mappedBy = "orders")
    private List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
}
