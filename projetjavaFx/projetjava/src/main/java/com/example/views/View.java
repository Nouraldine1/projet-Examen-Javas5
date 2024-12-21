package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.views.Interfaces.IView;

public abstract class View<T> implements IView<T>{

    protected Scanner scanner = new Scanner(System.in);
    
    // Méthode pour lister les éléments
    @Override
    public void lister() {
        List<T> items = getAll();
        
        if (items.isEmpty()) {
            System.out.println("Aucun élément n'est disponible.");
        } else {
            System.out.println("Liste des éléments :");
            for (T item : items) {
                System.out.println(item);
            }
        }
    }

    // Méthode d'ajout d'un élément (doit être implémentée par les sous-classes)
    @Override
    public abstract void  ajout();
    
    // Méthode abstraite pour obtenir tous les éléments
    protected abstract List<T> getAll();
}
