package ro.msg.learning.shop.Mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.RoleDto;
import ro.msg.learning.shop.Entities.Roles;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Roles mapRoleDtoToRole(RoleDto roleDto){
        return Roles.builder()
                .name(roleDto.getName())
                .build();
    }

    public RoleDto mapRoleToRoleDto(Roles roles){
        return RoleDto.builder()
                .name(roles.getName())
                .build();
    }
}
