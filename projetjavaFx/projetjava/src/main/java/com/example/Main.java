package com.example;

import java.util.Scanner;

import com.example.core.authentification.Authentification;
import com.example.core.data.entities.User;
import com.example.core.menu.Menu;

import javafx.application.Application;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // int choix;
        // ClientRepository clientRepository = new ClientRepository();
        // ClientService clientService = new ClientService(clientRepository);
        // ClientView clientView = new ClientView(clientService);
        // DetteRepository detteRepository = new DetteRepository();
        // DetteService detteService = new DetteService(detteRepository);
        // PaiementRepository paiementRepository = new PaiementRepository();
        // PaiementService paiementService = new PaiementService(paiementRepository);
        // PaiementView paiementView = new PaiementView(paiementService,
        // detteService,detteRepository);
        // ArticleRepository articleRepository = new ArticleRepository();
        // ArticleService articleService = new ArticleService(articleRepository);
        // ArticleView articleView = new ArticleView(articleService);

        // DetteView detteView = new DetteView(detteService, clientService,
        // articleService, paiementService,paiementView);

        // do {
        // System.out.println("1- Lister les client");
        // System.out.println("2- Ajouter un clients");
        // System.out.println("3- Lister les dettes");
        // System.out.println("4- Enregistrer une dette");
        // System.out.println("5- Lister les articles");
        // System.out.println("6- Enregistrer un article");
        // System.out.println("7- Enregistrer Paiement");
        // System.out.println("8- Lister Paiement");
        // System.out.println("9- Créer un user");
        // System.out.println("10- Associer un compte user à un client");
        // System.out.println("11- Liste de mes dettes");
        // System.out.println("12- Lister Paiement");
        // choix = scanner.nextInt();
        // scanner.nextLine();
        // switch (choix) {
        // case 1:
        // clientView.lister();
        // break;
        // case 2:
        // clientService.store(clientView.ajout());
        // break;
        // case 3:
        // detteView.lister();
        // break;
        // case 4:
        // detteService.store(detteView.ajout());
        // break;
        // case 5:
        // articleView.lister();
        // break;
        // case 6:
        // articleService.store(articleView.ajout());
        // break;
        // case 7:
        // paiementService.store(paiementView.ajout());
        // break;
        // case 8:
        // detteView.listerDettes();
        // break;
        // default:
        // break;
        // }
        // } while (choix != 0);

        // User user = Authentification.connexion();
        // if (user != null) {
        //    Menu.afficherMenu(user);
        // } else {
        //     System.out.println("Impossible d'afficher le menu, connexion échouée.");
        // }

    Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez le mode d'interface :");
        System.out.println("1. Console");
        System.out.println("2. JavaFX");

        int choix = scanner.nextInt();
        scanner.nextLine(); // Pour consommer le saut de ligne

        switch (choix) {
            case 1:
                // Mode console
                // Vous pouvez ici initialiser votre utilisateur et afficher le menu
                User user = Authentification.connexion();// Remplacez par votre logique d'initialisation de l'utilisateur
                Menu.afficherMenu(user);
                break;
            case 2:
                // Mode JavaFX
                Application.launch(JavaFXMenu.class, args); // Lancer JavaFX
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
                break;
        }

        scanner.close();
    }

}