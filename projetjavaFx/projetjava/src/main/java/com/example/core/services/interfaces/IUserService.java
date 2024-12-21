package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.User;

public interface IUserService{
    List<User> getAll();
    void store(User user);
}
