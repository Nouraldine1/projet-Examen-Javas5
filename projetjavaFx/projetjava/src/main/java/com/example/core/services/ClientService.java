package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.Client;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.services.interfaces.IClientService;

public class ClientService implements IClientService{
    private IClientRepository clientRepository;
   
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client ClientByPhone(String phone) {
        return clientRepository.findClientByPhone(phone);
    }

    @Override
    public Client ClientBySurname(String surname) {
        return clientRepository.findClientBySurname(surname);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.select();
    }
    @Override
    public void store(Client client){
        clientRepository.insert(client);
    }

    @Override
    public Client findClientById(int id) {
        return clientRepository.ClientById(id);
    }
}
