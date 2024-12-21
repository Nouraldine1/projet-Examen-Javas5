package com.example;

import com.example.core.data.entities.Client;
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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXMenu extends Application {

    private TableView<Client> clientTable;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Boutiquier");

        // Créer la barre de menu
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Fichier");
        MenuItem exitMenuItem = new MenuItem("Quitter");
        exitMenuItem.setOnAction(e -> primaryStage.close());
        menuFile.getItems().add(exitMenuItem);

        Menu menuHelp = new Menu("Aide");
        MenuItem aboutMenuItem = new MenuItem("À propos");
        aboutMenuItem.setOnAction(e -> showAboutDialog());
        menuHelp.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(menuFile, menuHelp);

        // Créer la table pour afficher les clients
        clientTable = new TableView<>();
        TableColumn<Client, String> nameColumn = new TableColumn<>("Nom");
        // nameColumn.setCellValueFactory(cellData ->
        // cellData.getValue().nameProperty());
        TableColumn<Client, String> phoneColumn = new TableColumn<>("Téléphone");

        clientTable.getColumns().addAll(nameColumn, phoneColumn);
        // clientTable.setItems(getClientList()); // Chargez la liste des clients

        // Créer des boutons pour les actions
        Button btnCreateClient = new Button("Créer un client");
        btnCreateClient.setOnAction(e -> {
            // Appeler la méthode clientView.ajout() ici
            clientView.ajout();
        });

        Button btnListClients = new Button("Lister les clients");
        btnListClients.setOnAction(e -> {
            // Logique pour lister les clients
            // Ex. : clientView.lister();
        });

        Button btnSearchClient = new Button("Rechercher un client par téléphone");
        btnSearchClient.setOnAction(e -> {
            // Appeler la méthode clientView.rechercherParTelephone() ici
            // Ex. : clientView.rechercherParTelephone();
        });

        // Créer un VBox pour les boutons
        VBox buttonLayout = new VBox(10, btnCreateClient, btnListClients, btnSearchClient);
        buttonLayout.setPrefWidth(200);

        // Créer le layout principal
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setLeft(buttonLayout);
        layout.setCenter(clientTable);

        // Créer et afficher la scène
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // private ObservableList<Client> getClientList() {
    // // Exemple de liste de clients (remplacez cela par vos données réelles)
    // ObservableList<Client> clients = FXCollections.observableArrayList();
    // clients.add(new Client("Alice", "0123456789"));
    // clients.add(new Client("Bob", "9876543210"));
    // return clients;
    // }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("À propos");
        alert.setHeaderText("Application de Gestion");
        alert.setContentText("Cette application gère les clients et les dettes.");
        alert.showAndWait();
    }
}
