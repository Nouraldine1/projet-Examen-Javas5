package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.User;
import com.ism.repository.IRepository;
import com.ism.services.AuthService;
import lombok.*;

@Getter
public class AuthServiceImpl implements AuthService {
    private final IRepository<User> userRepository;
    private User authenticatedUser;

    public AuthServiceImpl() {
        this.userRepository = RepositoryFactory.getRepository(User.class);
        this.authenticatedUser = null; 

    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findAll()
            .stream()
            .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
            .findFirst()
            .orElse(null);

        if (user != null) {
            this.authenticatedUser = user;
            return user;
        } else {
            return null;
        }
    }

    @Override
    public boolean logout() {
        if (this.authenticatedUser != null) {
            this.authenticatedUser = null;
            return true;
        } else {
            return false;
        }
    }
}
