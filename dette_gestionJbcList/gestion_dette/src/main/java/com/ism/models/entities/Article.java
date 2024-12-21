package com.ism.models.entities;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private String libelle;
    private double prix;
    private int qteStocks;
    
}
