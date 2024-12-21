package com.example.core.services;


import java.util.List;

import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.services.interfaces.IPaiementService;

public class PaiementService implements IPaiementService{
    private IPaiementRepository paiementRepository;

    public PaiementService(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public List<Paiement> getAll() {
       return paiementRepository.select();
    }

    @Override
    public void store(Paiement paiement) {
        paiementRepository.insert(paiement);
    }

    @Override
    public List<Paiement> ListfindPaiementById(int id) {
        return paiementRepository.ListPaiementById(id);
    }
    
}
