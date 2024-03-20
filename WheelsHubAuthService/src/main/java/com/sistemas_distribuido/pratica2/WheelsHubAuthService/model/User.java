package com.sistemas_distribuido.pratica2.WheelsHubAuthService.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;
    private String nome;
    private String password;
    private boolean isAdmin;

    public User(String login, String nome, String password) {
        this.login = login;
        this.nome = nome;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;}

    @Override
    public boolean isAccountNonLocked() {
        return true;}

    @Override
    public boolean isCredentialsNonExpired() {
        return true;}

    @Override
    public boolean isEnabled() {
        return true;
    }

}