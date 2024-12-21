package com.ism.models.entities;
import lombok.*;

import java.time.LocalDate;

import com.ism.models.enums.PaiementEtat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paiement {
    private int idPaiement;
    private double montant;
    private LocalDate date;
    private PaiementEtat etat;
    private Dette dette;
    
}
