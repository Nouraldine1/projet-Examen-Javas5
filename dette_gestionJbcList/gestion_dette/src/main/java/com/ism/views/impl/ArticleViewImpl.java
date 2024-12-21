package com.ism.views.impl;

import com.ism.models.entities.Article;
import com.ism.services.ArticleService;
import com.ism.services.impl.ArticleServiceImpl;
import com.ism.views.View;

import java.util.Scanner;

public class ArticleViewImpl implements View {
    private final ArticleService articleService = new ArticleServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        while (true) {
            System.out.println("\n=== Gestion des Articles ===");
            System.out.println("1. Ajouter un article");
            System.out.println("2. Lister tous les articles");
            System.out.println("3. Lister les articles disponibles");
            System.out.println("4. Mettre à jour le stock");
            System.out.println("5. Retour");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Libellé : ");
                    String libelle = scanner.nextLine();
                    System.out.print("Prix : ");
                    double prix = scanner.nextDouble();
                    System.out.print("Quantité en stock : ");
                    int qteStocks = scanner.nextInt();
                    articleService.createArticle(libelle, prix, qteStocks);
                    System.out.println("Article ajouté avec succès.");
                    break;
                case 2:
                    System.out.println("Liste des articles :");
                    articleService.listArticles().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Articles disponibles :");
                    articleService.listAvailableArticles().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("ID de l'article : ");
                    int articleId = scanner.nextInt();
                    System.out.print("Nouvelle quantité : ");
                    int newQuantity = scanner.nextInt();
                    articleService.updateStock(articleId, newQuantity);
                    System.out.println("Stock mis à jour.");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}