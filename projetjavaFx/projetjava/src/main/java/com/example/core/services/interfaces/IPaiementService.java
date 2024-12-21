package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.Paiement;

public interface IPaiementService{
    List<Paiement> getAll();
    void store(Paiement paiement);
    List<Paiement> ListfindPaiementById(int id);
}
