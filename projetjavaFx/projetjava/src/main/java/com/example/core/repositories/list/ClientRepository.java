package com.example.core.repositories.list;

import com.example.core.data.entities.Client;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repository.impl.RepositoryList;

public class ClientRepository extends RepositoryList<Client> implements IClientRepository {
    
    @Override
    public Client findClientByPhone(String phone) {
        return list.stream()
        .filter(client -> client.getTelephone().compareTo(phone) == 0)
        .findFirst()
        .orElse(null);
    }
    
    @Override
    public Client findClientBySurname(String surname) {
        return list.stream()
        .filter(client -> client.getSurname().compareTo(surname) == 0)
        .findFirst()
        .orElse(null);
    }

    @Override
    public Client ClientById(int id) {
        return list.stream()
        .filter(client -> client.getId()== 0)
        .findFirst()
        .orElse(null);
    }

    @Override
    public Client findClientByUserId(int userId) {
        for (Client client : list) {
            if(client.getId()==userId){
                return client;
            }
            
        }
        
        return null;
    }

}
