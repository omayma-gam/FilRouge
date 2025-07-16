package com.Application.FilRouge.Auth;

public class AuthentificationResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthentificationResponse() {
    }

    public AuthentificationResponse(String token) {
        this.token = token;
    }
}
