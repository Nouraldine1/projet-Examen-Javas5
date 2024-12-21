package com.example.core.services.interfaces;


import java.util.List;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;

public interface IDetteService{
    List<Dette> getAll();
    void store(Dette dette);
    Dette findDetteById(int id);
    List<Paiement> savePaiement(Paiement paiement);
    Paiement createPaiement(Paiement paiement);
    List<Dette> listerDetteNonSolder();
}
