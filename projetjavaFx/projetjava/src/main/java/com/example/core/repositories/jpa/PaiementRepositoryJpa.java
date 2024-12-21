package com.example.core.repositories.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repository.impl.RepositoryJpa;



public class PaiementRepositoryJpa extends  RepositoryJpa<Paiement> implements IPaiementRepository {

    public PaiementRepositoryJpa() {
        super(Paiement.class);
    }


    @Override
    public void insert(Paiement paiement) {
        entityManager.persist(paiement);
    }

    @Override
    public Paiement findById(int id) {
        return entityManager.find(Paiement.class, id);
    }

    @Override
    public List<Paiement> ListPaiementById(int detteId) {
        TypedQuery<Paiement> query = entityManager.createQuery(
                "SELECT p FROM Paiement p WHERE p.dette.id = :detteId", Paiement.class);
        query.setParameter("detteId", detteId);
        return query.getResultList();
    }

    @Override
    public List<Paiement> select() {
        TypedQuery<Paiement> query = entityManager.createQuery("SELECT p FROM Paiement p", Paiement.class);
        return query.getResultList();
    }

}
