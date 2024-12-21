package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Client;
import com.ism.models.enums.Role;
import com.ism.repository.IRepository;
import com.ism.services.ClientService;
import com.ism.services.UserService;
import java.util.List;
import java.util.stream.Collectors;

public class ClientServiceImpl implements ClientService {
    private IRepository<Client> clientRepository;
    private UserService userService;

    public ClientServiceImpl() {
        this.clientRepository = RepositoryFactory.getRepository(Client.class);
        this.userService = new UserServiceImpl();
    }

    @Override
    public void createClient(String nom, String telephone, String adresse) {
        Client client = new Client();
        client.setNom(nom);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        client.setMontantTotalDu(0.0);
        clientRepository.save(client);
    }

    @Override
    public Client createClientWithUser(String nom, String telephone, String adresse, String email, String identifiant, String password) {
        Client client = new Client();
        client.setNom(nom);
        client.setTelephone(telephone);
        client.setAdresse(adresse);
        client.setMontantTotalDu(0.0);
        clientRepository.save(client);
        
        userService.createUserForClient(client, email, identifiant, password, Role.CLIENT);
        return client;
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> listClientsWithUser() {
        return clientRepository.findAll().stream()
                .filter(client -> clientRepository.findById(client.getId()).getUser() != null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> listClientsWithoutUser() {
        return clientRepository.findAll().stream()
                .filter(client -> clientRepository.findById(client.getId()).getUser() == null)
                .collect(Collectors.toList());
    }

    @Override
    public Client findClientByTelephone(String telephone) {
        return clientRepository.findAll().stream()
                .filter(client -> client.getTelephone().equals(telephone))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Client findClientById(int id) {
        return clientRepository.findById(id);
    }

    @Override
    public Void updateClient(Client client) {
        Client existingClient = clientRepository.findById((long) client.getId());
        if (existingClient != null) {
            existingClient.setNom(client.getNom());
            existingClient.setTelephone(client.getTelephone());
            existingClient.setAdresse(client.getAdresse());
            clientRepository.update(existingClient);
        }
        return null;
    }
}
