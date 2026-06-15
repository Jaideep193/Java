package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.exceptions.EcommerceException;
import com.jaideep.ecommerce.models.Role;
import com.jaideep.ecommerce.models.User;
import com.jaideep.ecommerce.utils.IdGenerator;

public class AuthService {
    private final AppContext appContext;

    public AuthService(AppContext appContext) {
        this.appContext = appContext;
    }

    public User register(String username, String password, String email, String address, Role role) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new EcommerceException("Username and password are required.");
        }
        if (appContext.getAppData().getUsers().containsKey(username.toLowerCase())) {
            throw new EcommerceException("Username already exists.");
        }
        User user = new User(IdGenerator.newId("USR"), username, password, email, address, role);
        appContext.getAppData().getUsers().put(username.toLowerCase(), user);
        appContext.save();
        return user;
    }

    public User login(String username, String password) {
        User user = appContext.getAppData().getUsers().get(username.toLowerCase());
        if (user == null || !user.getPassword().equals(password)) {
            throw new EcommerceException("Invalid credentials.");
        }
        return user;
    }

    public void updateProfile(User user, String email, String address, String password) {
        if (user == null) {
            throw new EcommerceException("User is required.");
        }
        if (email != null && !email.isBlank()) {
            user.setEmail(email);
        }
        if (address != null && !address.isBlank()) {
            user.setAddress(address);
        }
        if (password != null && !password.isBlank()) {
            user.setPassword(password);
        }
        appContext.save();
    }
}
