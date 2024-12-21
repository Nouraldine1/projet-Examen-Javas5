package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.core.data.Enum.RoleUser;
import com.example.core.data.entities.User;
import com.example.core.services.interfaces.IUserService;
import com.example.views.Interfaces.IUserView;

public class UserView extends View<User> implements IUserView{

    Scanner scanner = new Scanner(System.in);

    IUserService userService;

    public UserView(IUserService userService) {
        this.userService = userService;
    }

    public void lister() {
        List<User> users = this.userService.getAll();

        if (users.isEmpty()) {
            System.out.println("Aucun User n'est disponible.");
        } else {
            System.out.println("Liste des users :");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    @Override
    public void  ajout() {
         // Créer une nouvelle instance User

        System.out.print("Renseigner l'email : ");
        String email = scanner.nextLine();
        while (email.isEmpty()) {
            System.out.println("L'email ne peut pas être vide.");
            email = scanner.nextLine();
        }
        

        System.out.print("Renseigner le login : ");
        String login = scanner.nextLine();
        while (login.isEmpty()) {
            System.out.println("Le login ne peut pas être vide.");
            login = scanner.nextLine();
         
        }
        

        System.out.print("Renseigner le mot de passe : ");
        String password = scanner.nextLine();
        while (password.isEmpty()) {
            System.out.println("Le mot de passe ne peut pas être vide.");
            password = scanner.nextLine();
         
        }
        

        RoleUser role = null;
        while (role == null) {
            System.out.print("Renseigner le rôle (ADMIN, BOUTIQUIER, CLIENT) : ");
            String roleInput = scanner.nextLine().toUpperCase(); // Convertir en majuscules pour la comparaison

            try {
                role = RoleUser.valueOf(roleInput); // Convertir la chaîne en enum
            } catch (IllegalArgumentException e) {
                System.out.println("Rôle invalide. Veuillez entrer ADMIN, BOUTIQUIER ou CLIENT.");
            }
        }
        User user = new User(email, login, password, role);
        userService.store(user);

    }

    @Override
    protected List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

}
