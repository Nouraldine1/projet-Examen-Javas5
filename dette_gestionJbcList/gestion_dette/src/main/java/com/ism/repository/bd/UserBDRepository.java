package com.ism.repository.bd;

import com.ism.models.entities.User;
import com.ism.repository.IRepository;

public interface UserBDRepository extends IRepository<User> {

    User login(String email, String password);

    User selectByLogin(String login);

}
