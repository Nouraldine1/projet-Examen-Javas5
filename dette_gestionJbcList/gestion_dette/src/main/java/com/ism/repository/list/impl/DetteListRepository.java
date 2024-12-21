package com.ism.repository.list.impl;

import com.ism.models.entities.Dette;
import com.ism.repository.IRepository;
import java.util.ArrayList;
import java.util.List;

public class DetteListRepository implements IRepository<Dette> {
    private List<Dette> dettes = new ArrayList<>();
    private static long idCounter = 1L;

    @Override
    public Dette findById(long userId) {
        return dettes.stream().filter(d -> d.getId() == userId).findFirst().orElse(null);
    }

    @Override
    public List<Dette> findAll() {
        return new ArrayList<>(dettes);
    }

    @Override
    public void save(Dette dette) {
        if (dette.getId() == 0) {
            dette.setId((int) idCounter++);
            dettes.add(dette);
        }
    }

    @Override
    public void update(Dette dette) {
        dettes.removeIf(d -> d.getId() == dette.getId());
        dettes.add(dette);
    }

    @Override
    public void delete(Long id) {
        dettes.removeIf(d -> d.getId() == id);
    }

 
}