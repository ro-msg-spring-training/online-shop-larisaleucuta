package ro.msg.learning.shop.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RoleDto {

    private Integer roleId;
    private String name;
}
