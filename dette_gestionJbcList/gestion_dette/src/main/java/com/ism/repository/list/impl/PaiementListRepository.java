package com.ism.repository.list.impl;

import com.ism.models.entities.Paiement;
import com.ism.repository.IRepository;
import java.util.ArrayList;
import java.util.List;

public class PaiementListRepository implements IRepository<Paiement> {
    private List<Paiement> paiements = new ArrayList<>();
    private static long idCounter = 1L;

    @Override
    public Paiement findById(long userId) {
        return paiements.stream().filter(p -> p.getIdPaiement() == userId).findFirst().orElse(null);
    }

    @Override
    public List<Paiement> findAll() {
        return new ArrayList<>(paiements);
    }

    @Override
    public void save(Paiement paiement) {
        if (paiement.getIdPaiement() == 0) {
            paiement.setIdPaiement((int) idCounter++);
            paiements.add(paiement);
        }
    }

    @Override
    public void update(Paiement paiement) {
        paiements.removeIf(p -> p.getIdPaiement() == paiement.getIdPaiement());
        paiements.add(paiement);
    }

    @Override
    public void delete(Long id) {
        paiements.removeIf(p -> p.getIdPaiement() == id);
    }

   
    
}