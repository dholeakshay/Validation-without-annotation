package com.example.user.user.Controller;

import com.example.user.user.Excaption.CustomException;
import com.example.user.user.Service.UserService;
import com.example.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new CustomException("Passwords do not match");
        }

        String userIdPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!Pattern.matches(userIdPattern, user.getUserId())) {
            throw new CustomException("Invalid userId format. It must contain at least one uppercase letter, one lowercase letter, one special character, and one digit.");
        }

        String contactPattern = "^[0-9]{10}$";
        if (!Pattern.matches(contactPattern, user.getContact())) {
            throw new CustomException("Invalid contact number format. It must contain exactly 10 digits.");
        }

        // Validate email format
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailPattern, user.getEmail())) {
            throw new CustomException("Invalid email format. ");
        }

        userService.registerUser(user);
        return "Data successfully registered";
    }
}
