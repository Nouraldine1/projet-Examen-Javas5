package com.example.core.repositories.jpa;

import java.util.List;

import javax.transaction.Transactional;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repository.impl.RepositoryJpa;



public class DetteRepositoryJpa extends  RepositoryJpa<Dette> implements IDetteRepository {


    public DetteRepositoryJpa() {
        super(Dette.class);
    }

    @Override
    public Dette detteById(int id) {
        return entityManager.find(Dette.class, id);
    }

    @Override
    @Transactional
    public void insert(Dette dette) {
        entityManager.persist(dette);
    }

    @Override
    public List<Dette> select() {
        return entityManager.createQuery("SELECT d FROM Dette d", Dette.class).getResultList();
    }

    @Override
    public List<Dette> detteNonSolder() {
        return entityManager.createQuery("SELECT d FROM Dette d WHERE d.montantRestant > 0", Dette.class).getResultList();
    }

    @Override
    public Paiement insertPaiement(Paiement paiement) {
        entityManager.persist(paiement);
        return paiement;
    }

    @Override
    public List<Paiement> enregistrePaiements(Paiement paiement) {
        return entityManager.createQuery("SELECT p FROM Paiement p WHERE p.dette.id = :detteId", Paiement.class)
                            .setParameter("detteId", paiement.getDette().getId())
                            .getResultList();
    }
}
