package com.example.core.repository;

import java.util.List;

public interface DataSourceJpa<T> extends IRepository<T>{
    void insert(T data);
    List<T> select();
    void close() ;
}
