package com.example.core.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String libelle;
    private Double prix;
    private int qteStock;
    public Article(String libelle, Double prix, int qteStock) {
        this.libelle = libelle;
        this.prix = prix;
        this.qteStock = qteStock;
    }
    
    public Article(){}
    
}
