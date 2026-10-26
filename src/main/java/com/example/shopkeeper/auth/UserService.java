package com.example.shopkeeper.auth;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository users;
    public UserService(UserRepository users) { this.users = users; }

    public User register(String fullName, String email, String password) {
        String normalized = email.trim().toLowerCase();
        if (users.existsByEmailIgnoreCase(normalized))
            throw new IllegalArgumentException("An account with this email already exists.");
        return users.save(new User(fullName.trim(), normalized, password));
    }
    public Optional<User> authenticate(String email, String password) {
        return users.findByEmailIgnoreCase(email.trim().toLowerCase())
                .filter(user -> user.getPassword().equals(password));
    }
    public Optional<User> find(Long id) { return users.findById(id); }
}
