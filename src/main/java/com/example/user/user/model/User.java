package com.example.user.user.model;

import lombok.Data;

@Data
public class User {
    private String userId;
    private String password;
    private String confirmPassword;
    private String email;
    private String contact;
}

