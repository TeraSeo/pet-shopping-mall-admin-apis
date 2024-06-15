package com.shoppingmall.users.service;

import com.shoppingmall.users.entity.User;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    Boolean createUser(User user);

    Boolean editUser(User user);
}
