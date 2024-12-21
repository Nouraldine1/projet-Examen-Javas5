package com.example.core.repositories.jpa;

import java.util.List;

import com.example.core.data.entities.Detail;
import com.example.core.repositories.list.interfaces.IDetailRepository;
import com.example.core.repository.impl.RepositoryJpa;

public class DetailRepositoryJpa extends  RepositoryJpa<Detail> implements IDetailRepository {
 

    public DetailRepositoryJpa() {
        super(Detail.class);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    // Récupérer un détail par ID
    @Override
    public Detail findById(int id) {
        return entityManager.find(Detail.class, id);
    }

    // Insérer un nouveau détail
    @Override
    public void insert(Detail data) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    // Sélectionner tous les détails
    @Override
    public List<Detail> select() {
        return entityManager.createQuery("SELECT d FROM Detail d", Detail.class).getResultList();
    }

}
