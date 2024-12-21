package com.ism.services;


import com.ism.models.entities.User;

public interface AuthService {

    User login(String email , String password);
    boolean logout();
    
}
