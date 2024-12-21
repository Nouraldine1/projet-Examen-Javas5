package com.ism.repository;

import java.util.List;



public interface IRepository<T> {
    T findById(long userId);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(Long id);
}
