package com.example.views;

import java.util.List;
import java.util.Scanner;

import com.example.core.data.entities.Article;
import com.example.core.services.interfaces.IArticleService;
import com.example.views.Interfaces.IArticleView;

public class ArticleView extends  View<Article> implements IArticleView{
    private IArticleService articleService;
    private Scanner scanner=new Scanner(System.in);
    public ArticleView(IArticleService articleService) {
        this.articleService = articleService;
    }
    

    public void lister() {
        List<Article> articles = this.articleService.getAll();

        if (articles.isEmpty()) {
            System.out.println("Aucun article n'est disponible.");
        } else {
            System.out.println("Liste des articles :");
            for (Article article : articles) {
                System.out.println(article);
            }
        }
    }

    public void  ajout(){
      
       
        System.out.println("Renseigner le libelle : ");

        String libelle = scanner.nextLine();
        
        
        System.out.println("Renseigner le prix : ");

        double prix = scanner.nextDouble();
     
        scanner.nextLine();

        System.out.println("Renseigner la quantité en stock : ");

        int qteStock = scanner.nextInt();
        
        scanner.nextLine();

        Article article = new Article(libelle, prix, qteStock);

        articleService.store(article);
        

    }

    @Override
    protected List<Article> getAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
