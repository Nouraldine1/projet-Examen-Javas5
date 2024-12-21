package com.example.views.Interfaces;

import com.example.core.data.entities.Client;

public interface IClientView extends  IView<Client>{
    void rechercherParTelephone();
    void AjoutDette();
    void AfficherDemandeDette();
    
}
