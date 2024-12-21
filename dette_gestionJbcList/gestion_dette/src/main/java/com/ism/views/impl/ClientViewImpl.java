package com.ism.views.impl;

import com.ism.models.entities.Client;
import com.ism.services.ClientService;
import com.ism.services.impl.ClientServiceImpl;
import com.ism.views.View;

import java.util.Scanner;

public class ClientViewImpl implements View {
    private final ClientService clientService = new ClientServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        while (true) {
            System.out.println("\n=== Gestion des Clients ===");
            System.out.println("1. Ajouter un client");
            System.out.println("2. Rechercher un client par ID");
            System.out.println("3. Lister tous les clients");
            System.out.println("4. Mettre à jour un client");
            System.out.println("5. Retour");
            System.out.print("Choix : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Téléphone : ");
                    String telephone = scanner.nextLine();
                    System.out.print("Adresse : ");
                    String adresse = scanner.nextLine();
                    clientService.createClient(nom, telephone, adresse);
                    System.out.println("Client ajouté avec succès.");
                    break;
                case 2:
                    System.out.print("ID du client : ");
                    int id = scanner.nextInt();
                    Client client = clientService.findClientById(id);
                    System.out.println(client != null ? client : "Client non trouvé.");
                    break;
                case 3:
                    System.out.println("Liste des clients :");
                    clientService.listClients().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("ID du client à mettre à jour : ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    Client existingClient = clientService.findClientById(clientId);
                    if (existingClient != null) {
                        System.out.print("Nouveau nom (" + existingClient.getNom() + ") : ");
                        String newNom = scanner.nextLine();
                        System.out.print("Nouveau téléphone (" + existingClient.getTelephone() + ") : ");
                        String newTelephone = scanner.nextLine();
                        System.out.print("Nouvelle adresse (" + existingClient.getAdresse() + ") : ");
                        String newAdresse = scanner.nextLine();
                        existingClient.setNom(newNom.isEmpty() ? existingClient.getNom() : newNom);
                        existingClient.setTelephone(newTelephone.isEmpty() ? existingClient.getTelephone() : newTelephone);
                        existingClient.setAdresse(newAdresse.isEmpty() ? existingClient.getAdresse() : newAdresse);
                        clientService.updateClient(existingClient);
                        System.out.println("Client mis à jour.");
                    } else {
                        System.out.println("Client non trouvé.");
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}