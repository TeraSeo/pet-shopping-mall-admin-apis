package com.shoppingmall.users.controller;

import com.shoppingmall.users.dto.UserDto;
import com.shoppingmall.users.entity.User;
import com.shoppingmall.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users/admin")
public class AdminUserController {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/fetch-users")
    public ResponseEntity<List<UserDto>> fetchUsers() {
        LOGGER.debug("get all users");
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = userService.getAllUsers();
        users.forEach(user -> {
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .role(user.getRole())
                    .isVerified(user.getIsVerified()).build();
            userDtoList.add(userDto);
        });
        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping("/create-user")
    public ResponseEntity<Boolean> createUser(@RequestBody User user) {
        LOGGER.debug("create an user");
        Boolean isUserCreated = userService.createUser(user);
        if (isUserCreated) {
            LOGGER.debug("user created");
        }
        return ResponseEntity.ok(isUserCreated);
    }

    @PutMapping("/edit-user")
    public ResponseEntity<Boolean> editUser(@RequestBody User user) {
        LOGGER.debug("edit user: " + user.toString());
        Boolean isEdited = userService.editUser(user);
        return ResponseEntity.ok(isEdited);
    }

    @DeleteMapping("/delete-users")
    public ResponseEntity<Boolean> deleteUser(@RequestHeader List<String> userIds) {
        LOGGER.debug("delete users");
        userIds.stream().forEach(
            userId -> {
                LOGGER.debug("id: " + userId);
            }
        );
        Boolean isDeleted = userService.deleteUser(userIds);
        return ResponseEntity.ok(isDeleted);
    }
}
