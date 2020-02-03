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
@Getter
@Builder
public class Location {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer locationId;

    @OneToMany(mappedBy = "shippedFrom",cascade = CascadeType.ALL)
    private List<Orders> ordersList = new ArrayList<Orders>();

    private String name;

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private List<Stock> stockList = new ArrayList<Stock>();

    @OneToOne(cascade = CascadeType.ALL)
    private Address locationAddress;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Revenue> revenueList = new ArrayList<>();

}
