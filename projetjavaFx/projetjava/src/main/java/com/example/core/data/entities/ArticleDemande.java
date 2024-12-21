package com.example.core.data.entities;

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
@Table(name = "article_demande")
public class ArticleDemande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int qteArticle;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "demande_dette_id")
    private DemandeDette demandeDette;
}
