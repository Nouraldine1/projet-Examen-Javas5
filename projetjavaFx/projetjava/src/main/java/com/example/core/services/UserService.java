package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.services.interfaces.IUserService;

public class UserService implements IUserService{

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.select();
    }

    @Override
    public void store(User user) {
        userRepository.insert(user);
    }
    
}
