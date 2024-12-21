package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.services.interfaces.IDetteService;

public class DetteService implements IDetteService {
    
    private IDetteRepository detteRepository;

    public DetteService(IDetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    @Override
    public List<Dette> getAll() {
        return detteRepository.select();
    }

    @Override
    public void store(Dette dette) {
        detteRepository.insert(dette);
    }

    @Override
    public Dette findDetteById(int id) {
        return detteRepository.detteById(id);
    }

    @Override
    public List<Paiement> savePaiement(Paiement paiement) {
        return detteRepository.enregistrePaiements(paiement);
    }

    @Override
    public Paiement createPaiement(Paiement paiement) {
        
        return detteRepository.insertPaiement(paiement);

    }

    @Override
    public List<Dette> listerDetteNonSolder() {
        return detteRepository.detteNonSolder();
    }
    
}
