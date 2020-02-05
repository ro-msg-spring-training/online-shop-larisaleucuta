package ro.msg.learning.shop.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ro.msg.learning.shop.DTO.RoleDto;
import ro.msg.learning.shop.DTO.UserDto;
import ro.msg.learning.shop.Entities.User;
import ro.msg.learning.shop.Exceptions.UserNotFoundException;
import ro.msg.learning.shop.Mapper.RoleMapper;
import ro.msg.learning.shop.Mapper.UserMapper;
import ro.msg.learning.shop.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public List<UserDto> getUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtoList.add(userMapper.mapUserToUserDto(user));
        }
        return userDtoList;
    }

    public UserDto createUser(String firstName, String lastName, String username, String password, String emailAddress, RoleDto roleDto) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .emailAddress(emailAddress)
                .role(roleMapper.mapRoleDtoToRole(roleDto))
                .build();

        userRepository.save(user);
        return userMapper.mapUserToUserDto(user);
    }

    public UserDto getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userMapper.mapUserToUserDto(userOptional.get());
        } else {
            throw new UserNotFoundException(id);
        }
    }

    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Integer id, String firstName, String lastName, String username, String password, String emailAddress, RoleDto roleDto) {
        if (userRepository.findById(id).isPresent()) {
            User existingUser = userRepository.findById(id).get();

            existingUser.setFirstName(firstName);
            existingUser.setLastName(lastName);
            existingUser.setUsername(username);
            existingUser.setPassword(password);
            existingUser.setEmailAddress(emailAddress);
            existingUser.setRole(roleMapper.mapRoleDtoToRole(roleDto));

            User updatedUser = userRepository.save(existingUser);

            return userMapper.mapUserToUserDto(updatedUser);
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
