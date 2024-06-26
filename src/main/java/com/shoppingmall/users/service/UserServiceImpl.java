package com.shoppingmall.users.service;

import com.shoppingmall.users.entity.User;
import com.shoppingmall.users.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Boolean createUser(User user) {
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (!u.isPresent()) {
            String password = user.getPassword();
            String encodedPassword = bCryptPasswordEncoder.encode(password);
            user.setPassword(encodedPassword);
            user.setIsVerified(false);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Boolean editUser(User user) {
        Optional<User> u = userRepository.findById(user.getId());
        User originalUser = u.get();
        user.updateModifiedDate();
        user.setIsVerified(originalUser.getIsVerified());
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean deleteUser(List<String> userIds) {
        userIds.stream().forEach(
            userId -> {
                userRepository.deleteById(Long.parseLong(userId));
            }
        );
        return true;
    }

}
