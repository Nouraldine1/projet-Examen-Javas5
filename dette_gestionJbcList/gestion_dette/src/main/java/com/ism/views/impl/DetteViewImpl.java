package com.ism.views.impl;

import com.ism.models.entities.Dette;
import com.ism.models.entities.Article;
import com.ism.services.DetteService;
import com.ism.services.impl.DetteServiceImpl;
import com.ism.services.ArticleService;
import com.ism.services.impl.ArticleServiceImpl;
import com.ism.views.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DetteViewImpl implements View {
    private final DetteService detteService = new DetteServiceImpl();
    private final ArticleService articleService = new ArticleServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        while (true) {
            System.out.println("\n=== Gestion des Dettes ===");
            System.out.println("1. Créer une dette");
            System.out.println("2. Trouver une dette par ID");
            System.out.println("3. Lister toutes les dettes");
            System.out.println("4. Lister les dettes d'un client");
            System.out.println("5. Mettre à jour une dette");
            System.out.println("6. Retour");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("ID du client : ");
                    int clientId = scanner.nextInt();
                    System.out.print("Nombre d'articles : ");
                    int articleCount = scanner.nextInt();
                    List<Article> articles = new ArrayList<>();
                    for (int i = 0; i < articleCount; i++) {
                        System.out.print("ID de l'article " + (i + 1) + " : ");
                        int articleId = scanner.nextInt();
                        Article article = articleService.listArticles().stream()
                                .filter(a -> a.getId() == articleId)
                                .findFirst()
                                .orElse(null);
                        if (article != null) articles.add(article);
                    }
                    detteService.createDette(clientId, LocalDate.now(), articles);
                    System.out.println("Dette créée avec succès.");
                    break;
                case 2:
                    System.out.print("ID de la dette : ");
                    int detteId = scanner.nextInt();
                    Dette dette = detteService.findDetteById(detteId);
                    System.out.println(dette != null ? dette : "Dette non trouvée.");
                    break;
                case 3:
                    System.out.println("Liste des dettes :");
                    detteService.listDettes().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("ID du client : ");
                    int clientDettesId = scanner.nextInt();
                    System.out.println("Dettes du client :");
                    detteService.listDettesByClient(clientDettesId).forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("ID de la dette à mettre à jour : ");
                    int updateDetteId = scanner.nextInt();
                    Dette existingDette = detteService.findDetteById(updateDetteId);
                    if (existingDette != null) {
                        System.out.print("Nouveau montant versé (" + existingDette.getMontantVerse() + ") : ");
                        double newMontantVerse = scanner.nextDouble();
                        existingDette.setMontantVerse(newMontantVerse);
                        existingDette.setMontantRestant(existingDette.getMontantTotal() - newMontantVerse);
                        detteService.updateDette(existingDette);
                        System.out.println("Dette mise à jour.");
                    } else {
                        System.out.println("Dette non trouvée.");
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}