package com.example.pets.models;

public class AuthResponse {
    private Client user;
    private String authorizeHeader;

    public AuthResponse() {
    }

    public AuthResponse(Client user, String authorizeHeader) {
        this.user = user;
        this.authorizeHeader = authorizeHeader;
    }

    public String getAuthorizeHeader() {
        return authorizeHeader;
    }

    public void setAuthorizeHeader(String authorizeHeader) {
        this.authorizeHeader = authorizeHeader;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
        this.user.setPassword("");
    }
}
