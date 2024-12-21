package com.example.views;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.example.core.data.Enum.EtatDemandeDette;
import com.example.core.data.entities.Article;
import com.example.core.data.entities.DemandeDette;
import com.example.core.services.ArticleService;
import com.example.core.services.DemandeDetteService;
import com.example.views.Interfaces.IDemandeDetteView;

public class DemandeDetteView extends View<DemandeDette> implements IDemandeDetteView {
    private final Scanner scanner = new Scanner(System.in);
    private final DemandeDetteService demandeDetteService;
    private final ArticleService articleService;

    public DemandeDetteView(DemandeDetteService demandeDetteService, ArticleService articleService) {
        this.demandeDetteService = demandeDetteService;
        this.articleService = articleService;
    }

    @Override
    public void listerDemandeDetteEncours() {
        List<DemandeDette> demandeDettes = this.demandeDetteService.getAll();

        if (demandeDettes.isEmpty()) {
            System.out.println("Aucune demande de dette n'est disponible.");
        } else {
            System.out.println("Liste des demandes de dette en cours :");
            for (DemandeDette demandeDette : demandeDettes) {
                if (demandeDette.getEtat() == EtatDemandeDette.ENCOURS) {
                    System.out.println(demandeDette);
                }
            }
        }
    }

    public void ajout() {
        double montantTotal = 0.0;
        String choix;
        int id;

        do {
            System.out.println("Saisissez l'id de l'article : ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un nombre entier.");
                scanner.next(); // Ignorer l'entrée non valide
            }
            id = scanner.nextInt();
            Article article = articleService.findArticleById(id);

            if (article != null) {
                montantTotal += article.getPrix();
                System.out.println("Montant total actuel : " + montantTotal);
            } else {
                System.out.println("Article non trouvé avec l'ID : " + id);
            }

            System.out.println("Voulez-vous ajouter un autre article ? (O/N) : ");
            scanner.nextLine(); // Consommer la nouvelle ligne après nextInt()
            choix = scanner.nextLine().trim().toUpperCase();
        } while (choix.equals("O"));

        EtatDemandeDette etat = null;
        do {
            System.out.println("Renseignez l'état de la dette (ENCOURS/ANNULER/VALIDER) : ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                etat = EtatDemandeDette.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Entrée non valide, veuillez réessayer.");
            }
        } while (etat == null);

        LocalDate date = LocalDate.now();
        DemandeDette demandeDette = new DemandeDette(date, montantTotal, etat);

        // Enregistrer la demande dans le service
        demandeDetteService.store(demandeDette);
        System.out.println("Demande de dette ajoutée avec succès : " + demandeDette);
    }

    @Override
    protected List<DemandeDette> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
}
