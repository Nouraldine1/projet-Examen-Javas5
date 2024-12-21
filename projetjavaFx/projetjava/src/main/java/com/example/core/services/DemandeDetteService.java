package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.DemandeDette;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.services.interfaces.IDemandeDetteService;

public class DemandeDetteService implements IDemandeDetteService{
    private IDemandeDetteRepository demandeDetteRepository;
    public DemandeDetteService(IDemandeDetteRepository demandeDetteRepository) {
        this.demandeDetteRepository = demandeDetteRepository;
    }

    @Override
    public List<DemandeDette> getAll() {
       return demandeDetteRepository.select();
    }

    @Override
    public void store(DemandeDette demandeDette) {
        demandeDetteRepository.insert(demandeDette);
    }

    @Override
    public DemandeDette findDemandeDetteById(int id) {
        return demandeDetteRepository.DemandeById(id);
    }
    
}
