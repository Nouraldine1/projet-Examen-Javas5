package com.example.core.repositories.list;



import com.example.core.data.entities.Detail;
import com.example.core.repositories.list.interfaces.IDetailRepository;
import com.example.core.repository.impl.RepositoryList;

public class DetailRepository extends RepositoryList<Detail> implements IDetailRepository {

    @Override
    public Detail findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
