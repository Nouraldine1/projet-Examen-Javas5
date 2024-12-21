package com.ism.models.entities;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

import com.ism.models.enums.DetteEtat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Dette {
    private int id ;
    private LocalDate date;
    private double montantTotal;
    private double montantVerse;
    private double montantRestant;
    private List<Article> articles;
    private DetteEtat etat;
    private Client client;
}
