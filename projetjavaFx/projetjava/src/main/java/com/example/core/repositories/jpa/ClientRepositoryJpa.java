package com.example.core.repositories.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import com.example.core.data.entities.Client;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repository.impl.RepositoryJpa;

public class ClientRepositoryJpa extends RepositoryJpa<Client> implements IClientRepository {
    public ClientRepositoryJpa() {
        super(Client.class);
    }

    @Override
    public Client findClientByPhone(String phone) {
        String query = "SELECT c FROM Client c WHERE c.telephone = :phone";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class);
        typedQuery.setParameter("phone", phone);
        return typedQuery.getResultStream().findFirst().orElse(null); // Récupère le premier ou retourne null si aucun
    }

    @Override
    public Client findClientBySurname(String surname) {
        String query = "SELECT c FROM Client c WHERE c.surname = :surname";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class);
        typedQuery.setParameter("surname", surname);
        return typedQuery.getResultStream().findFirst().orElse(null); // Récupère le premier ou retourne null si aucun
    }

    @Override
    public Client ClientById(int id) {
        return entityManager.find(Client.class, id); // Utilisation de la méthode `find` de l'EntityManager
    }

    @Override
    public void insert(Client data) {
        entityManager.getTransaction().begin();
        entityManager.persist(data); // Ajout de l'entité Client
        entityManager.getTransaction().commit(); // Validation de la transaction
    }

    @Override
    public List<Client> select() {
        String query = "SELECT c FROM Client c";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class);
        return typedQuery.getResultList(); // Retourne la liste des clients
    }


    @Override
    public Client findClientByUserId(int userId) {
        String query = "SELECT c FROM Client c WHERE c.userAccount.id = :userId";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class);
        typedQuery.setParameter("userId", userId);
        return typedQuery.getResultStream().findFirst().orElse(null); // Récupère le premier ou retourne null si aucun
    }


    @Override
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }

}
