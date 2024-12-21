package com.example.core.authentification;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.core.data.Enum.RoleUser;
import com.example.core.data.entities.User;

public class Authentification {

    // Simuler une base de données d'utilisateurs
    private static List<User> users = new ArrayList<>();

    static {
        // Ajouter des users pour l'exemple
        users.add(new User("Jean", "boutique", "123", RoleUser.BOUTIQUIER)); // Correct
        users.add(new User("Admin", "admin", "123", RoleUser.ADMIN)); // Utiliser l'énumération
        users.add(new User("Marie", "client", "123", RoleUser.CLIENT));
    }

    // Méthode pour demander la connexion
    public static User connexion() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer votre login :");
        String login = sc.nextLine();

        System.out.println("Veuillez entrer votre mot de passe :");
        String password = sc.nextLine();

        // Vérifier si l'User existe
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                System.out.println("Connexion réussie !");
                return user;
            }
        }

        // Si aucun utilisateur n'est trouvé
        System.out.println("Échec de la connexion, veuillez vérifier vos identifiants.");
        return null;
    }
}
