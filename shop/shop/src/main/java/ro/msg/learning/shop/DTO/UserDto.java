package ro.msg.learning.shop.DTO;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private RoleDto roleDto;
}
