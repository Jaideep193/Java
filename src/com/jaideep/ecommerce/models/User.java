package com.jaideep.ecommerce.models;

import java.io.Serializable;

public class User implements Serializable {
    private final String id;
    private final String username;
    private String password;
    private String email;
    private String address;
    private final Role role;

    public User(String id, String username, String password, String email, String address, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.role = role;
    }

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public Role getRole() { return role; }

    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
}
