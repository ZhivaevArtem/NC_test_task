package com.example.pets.models;

import java.util.ArrayList;
import java.util.List;

public class AuthResponse {
    private Client user;
    private List<String> roleList;

    public AuthResponse() {
    }

    public AuthResponse(Client user, List<String> roleList) {
        this.user = user;
        this.roleList = roleList;
    }

    public AuthResponse(Client user) {
        this.user = user;
        this.roleList = new ArrayList<>();
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
        this.user.setPassword("");
    }
}
