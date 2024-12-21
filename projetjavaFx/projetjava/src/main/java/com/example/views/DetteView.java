package com.example.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.core.data.Enum.EtatDette;
import com.example.core.data.entities.Client;
import com.example.core.data.entities.DemandeDette;
import com.example.core.data.entities.Dette;
import com.example.core.services.interfaces.IArticleService;
import com.example.core.services.interfaces.IClientService;
import com.example.core.services.interfaces.IDemandeDetteService;
import com.example.core.services.interfaces.IDetteService;
import com.example.core.services.interfaces.IPaiementService;
import com.example.views.Interfaces.IDetteView;

public class DetteView extends View<Dette> implements IDetteView {
    IClientService clientService;
    IDetteService detteService;
    IArticleService articleService;
    IPaiementService paiementService;
    IDemandeDetteService demandeDetteService;
    PaiementView paiementView;
    Scanner scanner = new Scanner(System.in);

    public DetteView(IDetteService detteService, IClientService clientService, IArticleService articleService,
            IPaiementService paiementService, PaiementView paiementView, IDemandeDetteService demandeDetteService) {
        this.detteService = detteService;
        this.clientService = clientService;
        this.articleService = articleService;
        this.paiementService = paiementService;
        this.paiementView = paiementView;
        this.demandeDetteService = demandeDetteService;
    }

    @Override
    public void lister() {
        List<Dette> dettes = this.detteService.getAll();

        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'est disponible.");
        } else {
            System.out.println("Liste des dettes :");
            for (Dette dette : dettes) {
                System.out.println(dette);
            }
        }
    }

    public void listerDettes() {
        List<Dette> dettes = detteService.getAll();

        if (dettes.isEmpty()) {
            System.out.println("Aucune dette n'est disponible.");
        } else {
            for (Dette dette : dettes) {
                System.out.println("Dette ID : " + dette.getId());
                System.out.println("Montant total : " + dette.getMontantTotal());
                System.out.println("Montant restant : " + dette.getMontantRestant());
                System.out.println("Client : " + dette.getClient().getSurname());

                // Appeler la méthode pour afficher les paiements associés
                // paiementView.afficherPaiementsPourDette(dette);

                System.out.println("-------------------------------");
            }
        }
    }

    @Override
    public void ajout() {

        int id;

        System.out.println("Renseigner l'id de la demande : ");
        id = scanner.nextInt();

        DemandeDette demandeDette = demandeDetteService.findDemandeDetteById(id);
        double montantTotal = demandeDette.getMontantTotal();
        
        scanner.nextLine();

        // Article article = articleService.findArticleById(id);


        System.out.println("Renseigner le Montant versé : ");

        int montantVerser = scanner.nextInt();
        scanner.nextLine();

        double montantRestant = montantTotal - montantVerser;

        EtatDette etat = null;
        do {
            System.out.println("Renseigner l'état de la dette (ENCOURS/SOLDER) : ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                etat = EtatDette.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Entrée non valide, veuillez réessayer.");
            }
        } while (etat == null);

        LocalDate date = LocalDate.now();
      
        System.out.println("Renseigner l'id du client : ");

        id = scanner.nextInt();

        Client client = clientService.findClientById(id);

        Dette dette = new Dette(montantTotal, montantVerser, montantRestant, etat, date, client);

        detteService.store(dette);
        


    }

    @Override
    protected List<Dette> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void AfficherDetteNonSolder() {
        List<Dette> listeDetteNonSolder = detteService.listerDetteNonSolder();
        for (Dette dette : listeDetteNonSolder) {
            System.out.println(dette);
        }
    }
}
