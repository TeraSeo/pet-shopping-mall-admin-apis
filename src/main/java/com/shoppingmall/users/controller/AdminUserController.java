package com.shoppingmall.users.controller;

import com.shoppingmall.users.entity.User;
import com.shoppingmall.users.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> fetchUsers() {
        LOGGER.debug("get all users");
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
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
        LOGGER.debug(user.toString());
        LOGGER.debug("edit an user");
        Boolean isEdited = userService.editUser(user);
        return ResponseEntity.ok(isEdited);
    }
}
