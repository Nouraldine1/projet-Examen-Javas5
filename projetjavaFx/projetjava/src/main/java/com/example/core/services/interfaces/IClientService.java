package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.Client;



public interface IClientService{
    Client ClientByPhone(String phone);
    Client ClientBySurname(String surname);
    List<Client> getAll();
    void store(Client client);
    Client findClientById(int id);
}
