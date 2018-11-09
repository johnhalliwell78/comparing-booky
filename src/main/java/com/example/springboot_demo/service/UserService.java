package com.example.springboot_demo.service;

import com.example.springboot_demo.model.User;
import com.example.springboot_demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
