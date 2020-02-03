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
public class Roles {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Id
    private Integer roleId;
    private String name;


    @OneToMany(mappedBy = "role")
    private List<User> user = new ArrayList<User>();

}
