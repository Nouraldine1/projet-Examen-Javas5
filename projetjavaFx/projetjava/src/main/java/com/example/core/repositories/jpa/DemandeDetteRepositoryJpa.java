package com.example.core.repositories.jpa;

import java.util.List;

import javax.persistence.TypedQuery;

import com.example.core.data.entities.DemandeDette;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repository.impl.RepositoryJpa;



public class DemandeDetteRepositoryJpa extends  RepositoryJpa<DemandeDette>  implements IDemandeDetteRepository {



    public DemandeDetteRepositoryJpa() {
        super(DemandeDette.class);
    }

    @Override
    public DemandeDette DemandeById(int id) {
        return entityManager.find(DemandeDette.class, id);
    }

    @Override
    public void insert(DemandeDette data) {
        if (data.getId() == null) {
            entityManager.persist(data);
        } else {
            entityManager.merge(data);
        }
    }

    @Override
    public List<DemandeDette> select() {
        TypedQuery<DemandeDette> query = entityManager.createQuery("SELECT d FROM DemandeDette d", DemandeDette.class);
        return query.getResultList();
    }

    
}
