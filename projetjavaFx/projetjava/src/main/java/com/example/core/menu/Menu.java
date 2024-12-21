package com.example.core.menu;

import java.util.Scanner;

import com.example.core.data.entities.User;
import com.example.core.factory.implement.FactoryRepository;
import com.example.core.factory.implement.FactoryRepositoryDb;
import com.example.core.factory.implement.FactoryRepositoryJpa;
import com.example.core.factory.implement.FactoryService;
import com.example.core.factory.implement.FactoryServiceDb;
import com.example.core.factory.implement.FactoryServiceJpa;
import com.example.core.factory.implement.FactoryView;
import com.example.views.Interfaces.IArticleView;
import com.example.views.Interfaces.IClientView;
import com.example.views.Interfaces.IDetteView;
import com.example.views.Interfaces.IPaiementView;
import com.example.views.Interfaces.IUserView;

public class Menu {

    public Menu() {
    }

    static FactoryRepository factoryRepository = new FactoryRepository();
    static FactoryRepositoryDb factoryRepositoryDb = new FactoryRepositoryDb();
    static FactoryRepositoryJpa factoryRepositoryJpa = new FactoryRepositoryJpa();
    static FactoryServiceJpa factoryServiceJpa = new FactoryServiceJpa(factoryRepositoryJpa);
    static FactoryService factoryService = new FactoryService(factoryRepository);
    static FactoryServiceDb factoryServiceDb = new FactoryServiceDb(factoryRepositoryDb);
    static FactoryView factoryView = new FactoryView(factoryService, factoryRepository);
    static IClientView clientView = factoryView.getInstanceClientView();
    static IArticleView articleView = factoryView.getInstanceArticleView();
    static IUserView userView = factoryView.getInstanceUserView();
    static IDetteView detteView = factoryView.getInstanceDette();
    static IPaiementView paiementView = factoryView.getInstancePaiementView();

    // Méthode pour afficher le menu selon le rôle de l'utilisateur
    public static void afficherMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        switch (user.getRole()) {
            case BOUTIQUIER:
                afficherMenuBoutiquier();
                break;
            case ADMIN:
                afficherMenuAdmin();
                break;
            case CLIENT:
                afficherMenuClient();
                break;
            default:
                System.out.println("Rôle inconnu");
        }
    }

    // Menu pour Boutiquier
    public static void afficherMenuBoutiquier() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Boutiquier :");
            System.out.println("1. Créer un client");
            System.out.println("2. Lister les clients");
            System.out.println("3. Rechercher un client par téléphone");
            System.out.println("4. Créer une dette");
            System.out.println("5. Lister les dettes non soldées d'un client");
            System.out.println("6. Enregistrer un paiement pour une dette");
            System.out.println("7. Lister les demandes de dette en cours");
            System.out.println("8. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le saut de ligne

            // Appeler les actions selon le choix de l'utilisateur
            demanderActionBoutiquier(choix);

        } while (choix != 5); // Continue tant que l'utilisateur ne choisit pas de quitter

    }

    // Menu pour Admin
    public static void afficherMenuAdmin() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Admin :");
            System.out.println("1. Créer un compte utilisateur");
            System.out.println("2. Lister les comptes utilisateurs");
            System.out.println("3. Lister les articles");
            System.out.println("4. Créer un articles");
            System.out.println("5. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            demanderActionAdmin(choix);

        } while (choix != 5);

    }

    // Menu pour Client
    public static void afficherMenuClient() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("Menu Client :");
            System.out.println("1. Lister vos dettes non soldées");
            System.out.println("2. Faire une demande de dette");
            System.out.println("3. Lister vos demandes de dette");
            System.out.println("4. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            demanderActionClient(choix);

        } while (choix != 4);

    }

    // Gérer les actions du Boutiquier
    public static void demanderActionBoutiquier(int choix) {
        switch (choix) {
            case 1:
                clientView.ajout();
                break;
            case 2:
                clientView.lister();
                break;
            case 3:
                clientView.rechercherParTelephone();
                break;
            case 4:
                detteView.ajout();
                break;
            case 5:
                detteView.AfficherDetteNonSolder();
                break;
            case 6:
                paiementView.ajout();
                break;
            case 7:
                // detteView.listerDemandesAvecFiltre();
                break;
            case 8:
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Choix invalide. Veuillez réessayer.");
        }

    }

    // Gérer les actions de l'Admin
    public static void demanderActionAdmin(int choix) {
        switch (choix) {
            case 1:
                userView.ajout();
                break;
            case 2:
                userView.lister();
                break;
            case 3:
                articleView.lister();
                break;
            case 4:
                articleView.ajout();
                break;
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Option non valide.");
        }
    }

    // Gérer les actions du Client
    public static void demanderActionClient(int choix) {
        switch (choix) {
            case 1:
                // Logique pour lister les dettes
                break;
            case 2:
                // Logique pour faire une demande de dette
                break;
            case 3:
                // Logique pour lister les demandes de dette
                break;
            case 5:
                System.out.println("Au revoir !");
                break;
            default:
                System.out.println("Option non valide.");
        }
    }
}
