package com.sistemas_distribuido.pratica2.WheelsHubAuthService.controller;

import com.sistemas_distribuido.pratica2.WheelsHubAuthService.model.User;
import com.sistemas_distribuido.pratica2.WheelsHubAuthService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
