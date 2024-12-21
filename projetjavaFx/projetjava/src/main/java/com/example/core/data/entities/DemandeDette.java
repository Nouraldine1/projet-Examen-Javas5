package com.example.core.data.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType; 
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.core.data.Enum.EtatDemandeDette;

import lombok.Data;

@Data
@Entity
@Table(name = "demande_dette")
public class DemandeDette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;
    private double  montantTotal;

    @Enumerated(EnumType.STRING)
    private EtatDemandeDette etat;

    public DemandeDette(LocalDate date, double montantTotal, EtatDemandeDette etat) {
        this.date = date;
        this.montantTotal = montantTotal;
        this.etat = etat;
    }

    public DemandeDette(){}
}
