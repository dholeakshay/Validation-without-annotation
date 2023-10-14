package com.example.user.user.Service;

import com.example.user.user.Excaption.CustomException;
import com.example.user.user.Repository.UserRepository;
import com.example.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (userRepository.isEmailExists(user.getEmail())) {
            throw new CustomException("Email already exists");
        }

        // Implement your logic for password validation, user ID validation, etc.
        userRepository.saveUser(user);
    }
}

