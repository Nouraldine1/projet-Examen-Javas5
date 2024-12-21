package com.ism.services;

import com.ism.models.entities.Paiement;
import java.time.LocalDate;
import java.util.List;

public interface PaiementService {
    void createPaiement(int detteId, double montant, LocalDate date);
    List<Paiement> listPaiementsByDette(int detteId);
    void updatePaiement(Paiement paiement);
}