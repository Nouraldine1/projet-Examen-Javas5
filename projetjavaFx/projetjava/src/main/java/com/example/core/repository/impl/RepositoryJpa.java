package com.example.core.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.example.core.ConfigLoader;
import com.example.core.repository.DataSourceJpa;



public class RepositoryJpa<T> implements DataSourceJpa<T> {
    protected Class<T> clazz;
    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;

    public RepositoryJpa(Class<T> clazz) {
        this.clazz = clazz;
        String persistenceUnit = ConfigLoader.getPersistenceUnit();  // Récupérer l'unité de persistence dynamique
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(T data) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(data);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<T> select() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}
