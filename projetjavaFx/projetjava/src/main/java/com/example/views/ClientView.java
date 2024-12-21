package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.core.data.entities.Client;
import com.example.core.data.entities.DemandeDette;
import com.example.core.services.interfaces.IClientService;
import com.example.views.Interfaces.IClientView;

public class ClientView extends View<Client> implements IClientView{
    
    private Scanner scanner=new Scanner(System.in);
   
    private DemandeDette demandeDette = new DemandeDette();
    private IClientService clientService;

    public ClientView(IClientService clientService){
        this.clientService = clientService;
    }

    public void lister() {
        List<Client> clients = this.clientService.getAll();

        if (clients.isEmpty()) {
            System.out.println("Aucun client n'est disponible.");
        } else {
            System.out.println("Liste des clients :");
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    public void  ajout(){
      
       
        System.out.println("Renseigner le surname : ");

        String surname = scanner.nextLine();
        
        
        System.out.println("Renseigner le telephone : ");

        String telephone = scanner.nextLine();
     
        
        System.out.println("Renseigner l'adresse : ");

        String address = scanner.nextLine();

        Client client=new Client(surname, telephone, address,null,null);

        clientService.store(client);
   

    }

    @Override
    public void rechercherParTelephone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le numéro de téléphone du client : ");
        String telephone = scanner.nextLine();

        // Recherche du client dans la base de données par téléphone
        Client client = clientService.ClientByPhone(telephone);

        if (client != null) {
            System.out.println("Client trouvé :");
            System.out.println("Nom : " + client.getSurname());
            System.out.println("Téléphone : " + client.getTelephone());
            System.out.println("Adresse : " + client.getAddress());
            // Afficher d'autres détails si nécessaire
        } else {
            System.out.println("Aucun client trouvé avec ce numéro de téléphone.");
        }
    }

    // @Override
    // public void AjoutDette() {
    //     List<DemandeDette> listeDemandeDettes = new ArrayList<>();

    //     System.out.println("Renseigner le Montant Total : ");

    //     demandeDette.setMontantTotal(scanner.nextInt());

    //     EtatDemandeDette etat = null;
    //     do {
    //         System.out.println("Renseigner l'état de la dette (ENCOURS/ANNULER/VALIDER) : ");
    //         String input = scanner.nextLine().trim().toUpperCase();

    //         try {
    //             etat = EtatDemandeDette.valueOf(input);
    //             demandeDette.setEtat(etat);
    //         } catch (IllegalArgumentException e) {
    //             System.out.println("Entrée non valide, veuillez réessayer.");
    //         }
    //     } while (etat == null);

    //     demandeDette.setDate(LocalDate.now());
    //     listeDemandeDettes.add(demandeDette);

    //     client.setDemandeDettes(listeDemandeDettes);
    // }

    // @Override
    // public void AfficherDemandeDette(){
    //     System.out.println(client.getDemandeDettes());
    // }

    // @Override
    // protected List<Client> getAll() {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }

    @Override
    protected List<Client> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AjoutDette() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AfficherDemandeDette() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
