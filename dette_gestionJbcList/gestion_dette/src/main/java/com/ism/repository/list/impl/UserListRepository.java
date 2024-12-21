package com.ism.repository.list.impl;

import com.ism.models.entities.User;
import com.ism.repository.IRepository;
import java.util.ArrayList;
import java.util.List;

public class UserListRepository implements IRepository<User> {
    private List<User> users = new ArrayList<>();
    private static long idCounter = 1L;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId((int) idCounter++);
            users.add(user);
        }
    }

    @Override
    public void update(User user) {
        users.removeIf(u -> u.getId() == user.getId());
        users.add(user);
    }

    @Override
    public void delete(Long id) {
        users.removeIf(u -> u.getId() == id);
    }

    @Override
    public User findById(long userId) {
        return users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);
    }
}