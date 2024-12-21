package com.ism.repository.list.impl;

import com.ism.models.entities.Client;
import com.ism.repository.IRepository;
import java.util.ArrayList;
import java.util.List;

public class ClientListRepository implements IRepository<Client> {
    private List<Client> clients = new ArrayList<>();
    private static long idCounter = 1L;

  
    @Override
    public Client findById(long userId) {
        return clients.stream().filter(c -> c.getId() == userId).findFirst().orElse(null);
    }
    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clients);
    }

    @Override
    public void save(Client client) {
        if (client.getId() == 0) {
            client.setId((int) idCounter++);
            clients.add(client);
        }
    }

    @Override
    public void update(Client client) {
        clients.removeIf(c -> c.getId() == client.getId());
        clients.add(client);
    }

    @Override
    public void delete(Long id) {
        clients.removeIf(c -> c.getId() == id);
    }

   
}