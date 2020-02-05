package ro.msg.learning.shop.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTO.ProductDto;
import ro.msg.learning.shop.DTO.UserDto;
import ro.msg.learning.shop.Exceptions.UserNotFoundException;
import ro.msg.learning.shop.Entities.User;
import ro.msg.learning.shop.Repositories.UserRepository;
import ro.msg.learning.shop.Services.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto.getFirstName(),userDto.getLastName(),userDto.getUsername(),userDto.getPassword(),userDto.getEmailAddress(),userDto.getRoleDto());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return userService.updateUser(id, userDto.getFirstName(),userDto.getLastName(),userDto.getUsername(),userDto.getPassword(),userDto.getEmailAddress(),userDto.getRoleDto());
    }
}
