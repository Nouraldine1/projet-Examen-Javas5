package com.example.core.repositories.list;

import com.example.core.data.entities.Client;
import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.repository.impl.RepositoryList;

public class UserRepository extends RepositoryList<User> implements IUserRepository{
    @Override
    public User findByLogin(String login) {
        for (User user : list) {
            if(user.getLogin().equals("login")){
                return user;
            }
        }
        return null;
    }

}
