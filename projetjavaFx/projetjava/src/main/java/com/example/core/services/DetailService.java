package com.example.core.services;

import java.util.List;

import com.example.core.data.entities.Detail;
import com.example.core.repositories.list.interfaces.IDetailRepository;
import com.example.core.services.interfaces.IDetailService;

public class DetailService implements IDetailService {
    private IDetailRepository detailRepository;
    public DetailService(IDetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
    @Override
    public List<Detail> getAll() {
        return detailRepository.select();
    }
    @Override
    public void store(Detail detail) {
        detailRepository.insert(detail);
    }


    
}
