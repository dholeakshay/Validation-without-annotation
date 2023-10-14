package com.example.user.user.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.user.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public void saveUser(User user) {
        users.put(user.getUserId(), user);
    }

    public boolean isEmailExists(String email) {
        return users.values().stream().anyMatch(user -> user.getEmail().equals(email));
    }
}
