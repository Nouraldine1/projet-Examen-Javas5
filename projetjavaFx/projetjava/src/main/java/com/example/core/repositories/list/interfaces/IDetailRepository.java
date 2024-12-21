package com.example.core.repositories.list.interfaces;

import com.example.core.data.entities.Detail;
import com.example.core.repository.IRepository;

public interface IDetailRepository extends IRepository<Detail> {
    Detail findById(int id);
}
