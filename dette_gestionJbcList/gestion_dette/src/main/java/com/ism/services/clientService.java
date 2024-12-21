package com.ism.services;

import com.ism.models.entities.Client;
import java.util.List;

public interface ClientService {
    void createClient(String nom, String telephone, String adresse);
    Client createClientWithUser(String nom, String telephone, String adresse, String email, String identifiant, String password);
    List<Client> listClients();
    List<Client> listClientsWithUser();
    List<Client> listClientsWithoutUser();
    Client findClientByTelephone(String telephone);
    Client findClientById(int id); 
    Void updateClient(Client client);
}