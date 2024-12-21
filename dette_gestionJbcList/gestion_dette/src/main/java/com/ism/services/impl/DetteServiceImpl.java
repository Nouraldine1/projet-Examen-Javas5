package com.ism.services.impl;

import com.ism.core.factory.RepositoryFactory;
import com.ism.models.entities.Dette;
import com.ism.models.entities.Client;
import com.ism.models.entities.Article;
import com.ism.models.enums.DetteEtat;
import com.ism.repository.IRepository;
import com.ism.services.DetteService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DetteServiceImpl implements DetteService {
    private IRepository<Dette> detteRepository = RepositoryFactory.getRepository(Dette.class);
    private IRepository<Client> clientRepository = RepositoryFactory.getRepository(Client.class);
    private IRepository<Article> articleRepository = RepositoryFactory.getRepository(Article.class);

    @Override
    public void createDette(int clientId, LocalDate date, List<Article> articles) {
        Client client = clientRepository.findById((long) clientId);
        if (client != null) {
            Dette dette = new Dette();
            dette.setClient(client);
            dette.setDate(date);
            double montantTotal = articles.stream().mapToDouble(Article::getPrix).sum();
            dette.setMontantTotal(montantTotal);
            dette.setMontantVerse(0.0);
            dette.setMontantRestant(montantTotal);
            dette.setEtat(DetteEtat.EN_COURS);
            dette.setArticles(articles);
            detteRepository.save(dette);

            // Mettre à jour le stock des articles
            for (Article article : articles) {
                Article dbArticle = articleRepository.findById((long) article.getId());
                if (dbArticle != null) {
                    dbArticle.setQteStocks(dbArticle.getQteStocks() - 1);
                    articleRepository.update(dbArticle);
                }
            }

            // Mettre à jour le montant total dû du client
            client.setMontantTotalDu(client.getMontantTotalDu() + montantTotal);
            clientRepository.update(client);
        }
    }

    @Override
    public Dette findDetteById(int id) {
        return detteRepository.findById((long) id);
    }

    @Override
    public List<Dette> listDettes() {
        return detteRepository.findAll();
    }

    @Override
    public List<Dette> listDettesByClient(int clientId) {
        return detteRepository.findAll().stream()
                .filter(dette -> dette.getClient().getId() == clientId)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDette(Dette dette) {
        Dette existingDette = detteRepository.findById((long) dette.getId());
        if (existingDette != null) {
            double oldMontantRestant = existingDette.getMontantRestant();
            double newMontantRestant = dette.getMontantRestant();
            double difference = oldMontantRestant - newMontantRestant;

            Client client = existingDette.getClient();
            client.setMontantTotalDu(client.getMontantTotalDu() - difference);
            clientRepository.update(client);

            detteRepository.update(dette);
        }
    }
}