package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Client;
import com.ism.models.entities.User;
import com.ism.models.enums.EtatCompte;
import com.ism.models.enums.Role;
import com.ism.repository.IRepository;
import com.ism.services.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private IRepository<User> userRepository;

    public UserServiceImpl() {
        this.userRepository = RepositoryFactory.getRepository(User.class);
    }

    @Override
    public void createUser(String email, String identifiant, String password, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setIdentifiant(identifiant);
        user.setPassword(password);
        user.setRole(role);
        user.setEtat(EtatCompte.ACTIF);
        userRepository.save(user);
    }

    @Override
    public void createUserForClient(Client client, String email, String identifiant, String password, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setIdentifiant(identifiant);
        user.setPassword(password);
        user.setRole(role);
        user.setEtat(EtatCompte.ACTIF);
        user.setClient(client);
        userRepository.save(user);
    }

    @Override
    public void activateUser(int userId) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.setEtat(EtatCompte.ACTIF);
            userRepository.save(user);
        }
    }

    @Override
    public void deactivateUser(int userId) {
        User user = userRepository.findById(userId);
        if (user != null) {
            user.setEtat(EtatCompte.INACTIF);
            userRepository.save(user);
        }
    }

    @Override
    public List<User> listActiveUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getEtat() == EtatCompte.ACTIF)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> listUsersByRole(Role role) {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == role)
                .collect(Collectors.toList());
    }
}