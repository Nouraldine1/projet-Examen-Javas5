package com.example.core.repositories.list.interfaces;

import java.util.List;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repository.IRepository;

public interface IDetteRepository extends IRepository<Dette> {
    Dette detteById(int id);
    List<Paiement> enregistrePaiements(Paiement paiement);
    Paiement insertPaiement(Paiement paiement);
    List<Dette> detteNonSolder();
}
