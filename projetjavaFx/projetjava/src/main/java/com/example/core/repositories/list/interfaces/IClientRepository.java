package com.example.core.repositories.list.interfaces;

import com.example.core.data.entities.Client;
import com.example.core.repository.IRepository;

public interface IClientRepository extends IRepository<Client> {
    Client findClientByPhone(String phone);
    Client findClientBySurname(String surname);
    Client ClientById(int id);
    Client findClientByUserId(int userId);
} 
