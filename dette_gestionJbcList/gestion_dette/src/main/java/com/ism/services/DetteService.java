package com.ism.services;

import com.ism.models.entities.Dette;
import com.ism.models.entities.Article;
import java.time.LocalDate;
import java.util.List;

public interface DetteService {
    void createDette(int clientId, LocalDate date, List<Article> articles);
    Dette findDetteById(int id);
    List<Dette> listDettes();
    List<Dette> listDettesByClient(int clientId);
    void updateDette(Dette dette);
}