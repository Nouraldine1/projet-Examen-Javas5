package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.core.data.entities.Detail;
import com.example.core.services.ArticleService;
import com.example.core.services.DetteService;
import com.example.core.services.interfaces.IDetailService;

public class DetailView {
    Scanner scanner = new Scanner(System.in);
    Detail detail = new Detail();
    private IDetailService detailService;
    private DetteService detteService;
    private ArticleService articleService;
    public DetailView(IDetailService detailService,DetteService detteService,ArticleService articleService) {
        this.detailService = detailService;
        this.detailService=detailService;
        this.articleService=articleService;
    }

    public void lister() {
        List<Detail> details = this.detailService.getAll();

        if (details.isEmpty()) {
            System.out.println("Aucune dette n'est disponible.");
        } else {
            System.out.println("Liste des details :");
            for (Detail detail : details) {
                System.out.println(detail);
            }
        }
    }

    public Detail ajout() {

        System.out.println("Renseigner le prix de vente  : ");

        detail.setPrixVente(scanner.nextInt());

        System.out.println("Renseigner la quantité : ");

        detail.setQuantite(scanner.nextInt());
        System.out.println("Renseigner l'id de l'article : ");
        int id;
        id=scanner.nextInt();
        detail.setArticle(articleService.findArticleById(id));

        System.out.println("Renseigner l'id de la dette : ");
        id = scanner.nextInt();
        detail.setDette(detteService.findDetteById(id));
        
        return detail;

    }
}
