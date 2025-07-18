package com.Application.FilRouge.DTO;

import com.Application.FilRouge.Model.Role;
import com.Application.FilRouge.Model.User;
import lombok.Value;

import java.io.Serializable;


public class UserDto implements Serializable {
    Long id;
    String username;
    String password;
    String email;
    Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}