package com.ism.views.impl;

import com.ism.models.entities.User;
import com.ism.services.AuthService;
import com.ism.services.impl.AuthServiceImpl;
import com.ism.views.View;

import java.util.Scanner;

public class AuthViewImpl implements View {
    private final AuthService authService = new AuthServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private User loggedInUser;

    @Override
    public void display() {
        while (loggedInUser == null) {
            System.out.println("\n=== Authentification ===");
            System.out.println("1. Connexion");
            System.out.println("2. Inscription");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Identifiant : ");
                    String identifiant = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String password = scanner.nextLine();
                    loggedInUser = authService.login(identifiant, password);
                    if (loggedInUser != null) {
                        System.out.println("Connexion réussie. Bienvenue, " + loggedInUser.getIdentifiant() + "!");
                        new MainViewImpl(loggedInUser).display();
                    } else {
                        System.out.println("Identifiant ou mot de passe incorrect.");
                    }
                    break;
                case 2:
                    System.out.print("Email : ");
                    String email = scanner.nextLine();
                    System.out.print("Identifiant : ");
                    String newIdentifiant = scanner.nextLine();
                    System.out.print("Mot de passe : ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Rôle (ADMIN/CLIENT) : ");
                    String role = scanner.nextLine().toUpperCase();
                    System.out.print("État (ACTIF/BLOQUE) : ");
                    String etat = scanner.nextLine().toUpperCase();
                    System.out.print("ID Client (optionnel, 0 si aucun) : ");
                    int clientId = scanner.nextInt();
                    authService.register(email, newIdentifiant, newPassword, role, etat, clientId == 0 ? null : clientId);
                    System.out.println("Utilisateur inscrit avec succès.");
                    break;
                case 3:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}