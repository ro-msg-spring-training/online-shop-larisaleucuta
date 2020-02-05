package ro.msg.learning.shop.Mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTO.UserDto;
import ro.msg.learning.shop.Entities.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .emailAddress(userDto.getEmailAddress())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .role(roleMapper.mapRoleDtoToRole(userDto.getRoleDto()))
                .build();
    }

    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .username(user.getUsername())
                .roleDto(roleMapper.mapRoleToRoleDto(user.getRole()))
                .build();
    }
}
