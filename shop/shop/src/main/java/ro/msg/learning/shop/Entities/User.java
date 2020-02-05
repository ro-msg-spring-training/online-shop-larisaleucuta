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
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(unique = true)
    private Integer user_id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;

    @ManyToOne
    private Roles role;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<Orders>();

}
