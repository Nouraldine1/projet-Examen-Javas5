package com.example.core.services.interfaces;

import java.util.List;

import com.example.core.data.entities.Detail;

public interface IDetailService {
    List<Detail> getAll();
    void store(Detail detail);
}
