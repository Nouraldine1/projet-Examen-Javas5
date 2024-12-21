package com.example.core.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.core.repository.IRepository;

public class RepositoryList<T> implements IRepository<T>{
    public List<T> list = new ArrayList<>();
    @Override
    public void insert(T data) {
        list.add(data);
    }

    @Override
    public List<T> select() {
        return list;
    }

}
