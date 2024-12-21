package com.example.core.repository;

import java.util.List;

public interface IRepository <T>{
    void insert(T data);

    List<T> select();
}
