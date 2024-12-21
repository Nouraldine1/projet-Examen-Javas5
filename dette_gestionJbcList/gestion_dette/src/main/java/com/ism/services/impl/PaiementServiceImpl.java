package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Paiement;
import com.ism.models.entities.Dette;
import com.ism.models.enums.DetteEtat;
import com.ism.models.enums.PaiementEtat;
import com.ism.repository.IRepository;
import com.ism.services.PaiementService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PaiementServiceImpl implements PaiementService {
    private IRepository<Paiement> paiementRepository = RepositoryFactory.getRepository(Paiement.class);
    private IRepository<Dette> detteRepository = RepositoryFactory.getRepository(Dette.class);

    @Override
    public void createPaiement(int detteId, double montant, LocalDate date) {
        Dette dette = detteRepository.findById((long) detteId);
        if (dette != null) {
            Paiement paiement = new Paiement();
            paiement.setDette(dette);
            paiement.setMontant(montant);
            paiement.setDate(date);
            paiement.setEtat(PaiementEtat.VALIDE);
            paiementRepository.save(paiement);

            // Mettre à jour la dette
            dette.setMontantVerse(dette.getMontantVerse() + montant);
            dette.setMontantRestant(dette.getMontantRestant() - montant);
            if (dette.getMontantRestant() <= 0) {
                dette.setEtat(DetteEtat.SOLDEE);
            }
            detteRepository.update(dette);
        }
    }

    @Override
    public List<Paiement> listPaiementsByDette(int detteId) {
        return paiementRepository.findAll().stream()
                .filter(paiement -> paiement.getDette().getId() == detteId)
                .collect(Collectors.toList());
    }

    @Override
    public void updatePaiement(Paiement paiement) {
        Paiement existingPaiement = paiementRepository.findById((long) paiement.getIdPaiement());
        if (existingPaiement != null) {
            double oldMontant = existingPaiement.getMontant();
            double newMontant = paiement.getMontant();
            double difference = newMontant - oldMontant;

            Dette dette = existingPaiement.getDette();
            dette.setMontantVerse(dette.getMontantVerse() + difference);
            dette.setMontantRestant(dette.getMontantRestant() - difference);
            if (dette.getMontantRestant() <= 0) {
                dette.setEtat(DetteEtat.SOLDEE);
            } else {
                dette.setEtat(DetteEtat.EN_COURS);
            }
            detteRepository.update(dette);

            paiementRepository.update(paiement);
        }
    }
}