package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.User;
import com.ism.models.entities.Client;
import com.ism.models.enums.EtatCompte;
import com.ism.models.enums.Role;
import com.ism.repository.IRepository;
import com.ism.services.AuthService;

public class AuthServiceImpl implements AuthService {
    private IRepository<User> userRepository = RepositoryFactory.getRepository(User.class);
    private IRepository<Client> clientRepository = RepositoryFactory.getRepository(Client.class);

    @Override
    public User login(String identifiant, String password) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getIdentifiant().equals(identifiant) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        return user;
    }

    @Override
    public void register(String email, String identifiant, String password, String role, String etat, Integer clientId) {
        User user = new User();
        user.setEmail(email);
        user.setIdentifiant(identifiant);
        user.setPassword(password);
        user.setRole(Role.valueOf(role));
        user.setEtat(EtatCompte.valueOf(etat));
        if (clientId != null) {
            Client client = clientRepository.findById((long) clientId);
            user.setClient(client);
        }
        userRepository.save(user);
    }
}