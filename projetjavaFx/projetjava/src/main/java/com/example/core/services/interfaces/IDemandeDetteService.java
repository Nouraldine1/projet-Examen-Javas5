package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.DemandeDette;

public interface IDemandeDetteService {
    List<DemandeDette> getAll();
    void store(DemandeDette demandeDette);
    DemandeDette findDemandeDetteById(int id);
}
