package com.ism;

import com.ism.services.impl.UserServiceImpl;
import com.ism.models.entities.User;
import com.ism.services.impl.*;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String BOUTIQUIER = "BOUTIQUIER";
    private static final String ADMIN = "ADMIN";
    private static final String CLIENT = "CLIENT";

    public static void main(String[] args) {
        AuthServiceImpl authService = new AuthServiceImpl();

        System.out.println("Bienvenue dans le système de gestion des dettes !");
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();
        User user = authService.login(email, password);

        if (user != null) {
            System.out.println("Connexion réussie!");
            System.out.println("Vous êtes connecté en tant que " + user.getEmail());
            if (user != null) {
                System.out.println("Connexion réussie ! Bienvenue, " + user.getEmail());
                switch (user.getRole()) {
                    case BOUTIQUIER:
                        menuBoutiquier();
                        break;
                    case ADMIN:
                        menuAdmin();
                        break;
                    case CLIENT:
                        menuClient();
                        break;
                }
            } 
        }
    }

    private static void menuBoutiquier() {
        ClientServiceImpl clientService = new ClientServiceImpl();
        DetteServiceImpl detteService = new DetteServiceImpl();

        int choix;
        do {
            System.out.println("\nMenu Boutiquier :");
            System.out.println("1. Créer un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Créer une dette");
            System.out.println("4. Enregistrer un paiement");
            System.out.println("5. Quitter");

            System.out.print("Votre choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 5);
    }

    private static void menuAdmin() {
        UserServiceImpl userService = new UserServiceImpl();

        int choix;
        do {
            System.out.println("\nMenu Admin :");
            System.out.println("1. Créer un compte utilisateur");
            System.out.println("2. Activer/Désactiver un compte utilisateur");
            System.out.println("3. Afficher les comptes actifs");
            System.out.println("4. Quitter");

            System.out.print("Votre choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                   userService.findAll();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 4);
    }

    private static void menuClient() {
        DetteServiceImpl detteService = new DetteServiceImpl();

        int choix;
        do {
            System.out.println("\nMenu Client :");
            System.out.println("1. Lister mes dettes non soldées");
            System.out.println("2. Faire une demande de dette");
            System.out.println("3. Quitter");

            System.out.print("Votre choix : ");
            choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Déconnexion...");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 3);
    }
}