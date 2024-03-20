package com.sistemas_distribuido.pratica2.WheelsHubAuthService.repository;

import com.sistemas_distribuido.pratica2.WheelsHubAuthService.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);

}
