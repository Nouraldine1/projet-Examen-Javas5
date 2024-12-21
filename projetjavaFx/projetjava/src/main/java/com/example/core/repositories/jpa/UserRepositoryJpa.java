package com.example.core.repositories.jpa;


import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.repository.impl.RepositoryJpa;

public class UserRepositoryJpa extends  RepositoryJpa<User> implements IUserRepository{

    public UserRepositoryJpa() {
        super(User.class);
        //TODO Auto-generated constructor stub
    }

    @Override
    public User findByLogin(String login) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
