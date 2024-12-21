package com.ism.core.factory;

import com.ism.repository.IRepository;
import com.ism.repository.bd.impl.*;
import com.ism.repository.list.impl.*;
import com.ism.database.PersistenceConfig;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class RepositoryFactory {

    private static final Map<String, IRepository<?>> REPOSITORY_CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> IRepository<T> getRepository(Class<T> entityClass) {
        PersistenceConfig persistenceConfig = new PersistenceConfig();
        String repositoryType = persistenceConfig.getRepositoryType();
        String cacheKey = repositoryType + ":" + entityClass.getSimpleName();

        if (REPOSITORY_CACHE.containsKey(cacheKey)) {
            return (IRepository<T>) REPOSITORY_CACHE.get(cacheKey);
        } else {
            IRepository<T> repository = createRepository(entityClass, repositoryType);
            REPOSITORY_CACHE.put(cacheKey, repository);
            return repository;
        }
    }

    private static <T> IRepository<T> createRepository(Class<T> entityClass, String repositoryType) {
        if ("bd".equals(repositoryType)) {
            return createBDRepository(entityClass);
        } else if ("list".equals(repositoryType)) {
            return createListRepository(entityClass);
        } else {
            throw new RuntimeException("Type de repository inconnu : " + repositoryType);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> IRepository<T> createBDRepository(Class<T> entityClass) {
        switch (entityClass.getSimpleName()) {
            case "Article":
                return (IRepository<T>) new ArticleBDRepositoryImpl();
            case "Dette":
                return (IRepository<T>) new DetteBDRepositoryImpl();
            case "Paiement":
                return (IRepository<T>) new PaiementBDRepositoryImpl();
            case "User":
                return (IRepository<T>) new UserBDRepositoryImpl();
            default:
                throw new RuntimeException("Aucun repository BD pour l'entité : " + entityClass.getSimpleName());
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> IRepository<T> createListRepository(Class<T> entityClass) {
        switch (entityClass.getSimpleName()) {
            case "Article":
                return (IRepository<T>) new ArticleListRepository();
            case "Client":
                return (IRepository<T>) new ClientListRepository();
            case "Dette":
                return (IRepository<T>) new DetteListRepository();
            case "User":
                return (IRepository<T>) new UserListRepository();
            default:
                throw new RuntimeException("Aucun repository LIST pour l'entité : " + entityClass.getSimpleName());
        }
    }
}
