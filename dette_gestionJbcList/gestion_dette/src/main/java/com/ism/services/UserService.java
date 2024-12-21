package com.ism.services;

import java.util.List;

import com.ism.models.entities.User;

public interface UserService {
        User findById(Long id);
        List<User> findAll();
        void save(User user);
        void update(User user);
        void delete(Long id);
    
}
