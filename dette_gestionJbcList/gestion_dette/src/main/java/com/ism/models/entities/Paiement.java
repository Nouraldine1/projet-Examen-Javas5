package com.ism.models.entities;
import lombok.*;

import java.util.Date;

import com.ism.models.enums.PaiementEtat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paiement {
    private int idPaiement;
    private double montant;
    private Date date;
    private PaiementEtat etat;
    
}
