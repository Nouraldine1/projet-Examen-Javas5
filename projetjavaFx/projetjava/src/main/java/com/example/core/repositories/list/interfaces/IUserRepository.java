package com.example.core.repositories.list.interfaces;

import com.example.core.data.entities.User;
import com.example.core.repository.IRepository;

public interface IUserRepository extends IRepository<User>{
    User findByLogin(String login);
}
