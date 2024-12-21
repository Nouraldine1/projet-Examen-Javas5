package com.ism.views.impl;

import com.ism.models.entities.Paiement;
import com.ism.services.PaiementService;
import com.ism.services.impl.PaiementServiceImpl;
import com.ism.views.View;

import java.time.LocalDate;
import java.util.Scanner;

public class PaiementViewImpl implements View {
    private final PaiementService paiementService = new PaiementServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        while (true) {
            System.out.println("\n=== Gestion des Paiements ===");
            System.out.println("1. Ajouter un paiement");
            System.out.println("2. Lister les paiements d'une dette");
            System.out.println("3. Mettre à jour un paiement");
            System.out.println("4. Retour");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID de la dette : ");
                    int detteId = scanner.nextInt();
                    System.out.print("Montant : ");
                    double montant = scanner.nextDouble();
                    paiementService.createPaiement(detteId, montant, LocalDate.now());
                    System.out.println("Paiement ajouté avec succès.");
                    break;
                case 2:
                    System.out.print("ID de la dette : ");
                    int listDetteId = scanner.nextInt();
                    System.out.println("Paiements de la dette :");
                    paiementService.listPaiementsByDette(listDetteId).forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID du paiement à mettre à jour : ");
                    int paiementId = scanner.nextInt();
                    Paiement paiement = paiementService.listPaiementsByDette(0).stream()
                            .filter(p -> p.getIdPaiement() == paiementId)
                            .findFirst()
                            .orElse(null);
                    if (paiement != null) {
                        System.out.print("Nouveau montant (" + paiement.getMontant() + ") : ");
                        double newMontant = scanner.nextDouble();
                        paiement.setMontant(newMontant);
                        paiementService.updatePaiement(paiement);
                        System.out.println("Paiement mis à jour.");
                    } else {
                        System.out.println("Paiement non trouvé.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}