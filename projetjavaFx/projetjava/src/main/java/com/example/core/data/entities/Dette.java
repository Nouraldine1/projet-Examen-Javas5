package com.example.core.data.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.core.data.Enum.EtatDette;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "dettes")
@EqualsAndHashCode
public class Dette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double montantTotal;
    private double montantVerser;
    private double montantRestant;

    @Enumerated(EnumType.STRING)
    private EtatDette etat;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;


    public Dette(double montantTotal, double montantVerser, double montantRestant, EtatDette etat, LocalDate date,
            Client client) {
        this.montantTotal = montantTotal;
        this.montantVerser = montantVerser;
        this.montantRestant = montantRestant;
        this.etat = etat;
        this.date = date;
        this.client = client;
    }

    public Dette() {
        
    }


}
