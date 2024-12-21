package com.example.core.data.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "paiements")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private int montantPayer;

    @ManyToOne
    @JoinColumn(name = "dette_id")
    private Dette dette;

    public Paiement(LocalDate date, int montantPayer, Dette dette) {
        this.date = date;
        this.montantPayer = montantPayer;
        this.dette = dette;
    }

    public Paiement(){}
}
