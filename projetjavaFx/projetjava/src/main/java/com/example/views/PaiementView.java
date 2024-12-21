package com.example.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.services.interfaces.IDetteService;
import com.example.core.services.interfaces.IPaiementService;
import com.example.views.Interfaces.IPaiementView;

public class PaiementView extends View<Paiement> implements IPaiementView{
    private final Scanner scanner=new Scanner(System.in);
    IPaiementService paiementService;
    IDetteService detteService;
    Dette dette = new Dette();
   
    IDetteRepository detteRepository ;
    public PaiementView(IPaiementService paiementService, IDetteService detteService, IDetteRepository detteRepository) {
        this.paiementService = paiementService;
        this.detteService = detteService;
        this.detteRepository = detteRepository;
    }

  
    

    public void lister() {
        List<Paiement> paiements = this.paiementService.getAll();

        if (paiements.isEmpty()) {
            System.out.println("Aucun paiement n'est disponible.");
        } else {
            System.out.println("Liste des paiements :");
            for (Paiement paiement : paiements) {
                System.out.println(paiement);
            }
        }
    }

    // public void afficherPaiementsPourDette(Dette dette) {
    //     List<Paiement> paiements = dette.getPaiements();
        
    //     if (paiements.isEmpty()) {
    //         System.out.println("Aucun paiement pour cette dette.");
    //     } else {
    //         System.out.println("Liste des paiements pour la dette ID " + dette.getId() + " :");
    //         for (Paiement paiement : paiements) {
    //             System.out.println(paiement);
    //         }
    //     }
    // }
    @Override
    public void  ajout(){
      
        LocalDate date = LocalDate.now();
        
        System.out.println("Renseigner le montant à payer : ");
        int montant = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Renseigner l'id de la dette : ");
        int id;
        id=scanner.nextInt();
        scanner.nextLine();
        Dette dette = detteService.findDetteById(id);

        double montantVerser = dette.getMontantVerser() + montant;

        dette.setMontantVerser(montantVerser);

        double montantRestant = dette.getMontantRestant() - montantVerser;

        dette.setMontantRestant(montantRestant);

        if (dette == null) {
            System.out.println("Dette non trouvée.");
        }

        Paiement paiement = new Paiement(date, montant, dette);
        

        paiementService.store(paiement);

    }

    @Override
    protected List<Paiement> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
