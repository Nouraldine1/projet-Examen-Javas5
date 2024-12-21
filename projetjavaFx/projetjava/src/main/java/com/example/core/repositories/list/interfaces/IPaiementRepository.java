package com.example.core.repositories.list.interfaces;

import java.util.List;

import com.example.core.data.entities.Paiement;
import com.example.core.repository.IRepository;

public interface IPaiementRepository extends IRepository<Paiement> {
    List<Paiement> ListPaiementById(int id);
    Paiement findById(int id);
}
