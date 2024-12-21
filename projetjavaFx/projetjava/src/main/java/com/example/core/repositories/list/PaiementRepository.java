package com.example.core.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repository.impl.RepositoryList;

public class PaiementRepository extends RepositoryList<Paiement> implements IPaiementRepository {
    public List<Paiement> listePaiement = new ArrayList<>();
    @Override
    public List<Paiement> ListPaiementById(int id){
        for (Paiement paiement : list) {
            if (paiement.getId()==id) {
                listePaiement.add(paiement);
                return listePaiement;
            }
        }
        return listePaiement;
    }
    @Override
    public Paiement findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
