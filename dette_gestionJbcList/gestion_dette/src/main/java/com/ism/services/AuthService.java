package com.ism.services;

import com.ism.models.entities.User;

public interface AuthService {
    User login(String identifiant, String password);
    void register(String email, String identifiant, String password, String role, String etat, Integer clientId);
}