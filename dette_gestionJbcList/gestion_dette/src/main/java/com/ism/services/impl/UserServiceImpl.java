package com.ism.services.impl;

import java.util.List;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.User;
import com.ism.repository.IRepository;
import com.ism.services.UserService;

public class UserServiceImpl implements UserService {
    private final IRepository<User> userRepository;
    public Object findAll;

    public UserServiceImpl() {
        // Obtenir dynamiquement le repository pour User
        this.userRepository = RepositoryFactory.getRepository(User.class);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }
    
}
