package com.ism.views.impl;

import com.ism.models.entities.User;
import com.ism.views.View;

import java.util.Scanner;

public class MainViewImpl implements View {
    private final User loggedInUser;
    private final Scanner scanner = new Scanner(System.in);

    public MainViewImpl(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public void display() {
        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("Utilisateur connecté : " + loggedInUser.getIdentifiant());
            System.out.println("1. Gestion des articles");
            System.out.println("2. Gestion des clients");
            System.out.println("3. Gestion des dettes");
            System.out.println("4. Gestion des paiements");
            System.out.println("5. Déconnexion");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    new ArticleViewImpl().display();
                    break;
                case 2:
                    new ClientViewImpl().display();
                    break;
                case 3:
                    new DetteViewImpl().display();
                    break;
                case 4:
                    new PaiementViewImpl().display();
                    break;
                case 5:
                    System.out.println("Déconnexion...");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}