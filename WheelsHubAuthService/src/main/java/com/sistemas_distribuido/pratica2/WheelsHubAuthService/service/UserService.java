package com.sistemas_distribuido.pratica2.WheelsHubAuthService.service;

import com.sistemas_distribuido.pratica2.WheelsHubAuthService.config.security.TokenService;
import com.sistemas_distribuido.pratica2.WheelsHubAuthService.model.LoginResponse;
import com.sistemas_distribuido.pratica2.WheelsHubAuthService.model.User;
import com.sistemas_distribuido.pratica2.WheelsHubAuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    @Autowired
    public UserService(UserRepository repository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseEntity<?> login(User entity) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(entity.getLogin(), entity.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        LoginResponse response = new LoginResponse(user, token);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> register(User entity) {
        if (repository.findByLogin(entity.getLogin()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(entity.getPassword());
        entity.setPassword(encryptedPassword);
        this.repository.save(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getUserByEmail(String email) {
        User user = repository.findByLogin(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> getAllUsers() {
        List<User> users = repository.findAll();

        return ResponseEntity.ok(users);
    }

    public ResponseEntity<?> deleteUser(UUID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
