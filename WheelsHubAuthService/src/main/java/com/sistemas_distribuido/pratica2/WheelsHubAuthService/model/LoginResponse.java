package com.sistemas_distribuido.pratica2.WheelsHubAuthService.model;

public class LoginResponse {
    private String login;
    private String nome;
    private String token;

    public LoginResponse(User user, String token) {
        this.login = user.getLogin();
        this.nome = user.getNome();
        this.token = token;
    }

    // getters e setters
}
