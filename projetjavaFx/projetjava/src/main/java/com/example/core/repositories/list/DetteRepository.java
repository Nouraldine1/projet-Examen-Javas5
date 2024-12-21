package com.example.core.repositories.list;

import java.util.ArrayList;
import java.util.List;

import com.example.core.data.entities.Dette;
import com.example.core.data.entities.Paiement;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repository.impl.RepositoryList;

public class DetteRepository extends RepositoryList<Dette> implements IDetteRepository {
    List<Paiement> listePaiement = new ArrayList<>();

    @Override
    public Dette detteById(int id) {
        for (Dette dette : list) {
            if (dette.getId()==id) {
                
                return dette;
            }
        }
        return null;
    }

    @Override
    public List<Paiement> enregistrePaiements(Paiement paiement) {
        listePaiement.add(paiement);
        return listePaiement;
    }

    @Override
    public Paiement insertPaiement(Paiement paiement) {
        return paiement;
    }

    @Override
    public List<Dette> detteNonSolder() {
        List<Dette> listeDetteNonSolde = new ArrayList<>();
        for (Dette dette : list) {
            if (dette.getEtat().equals("SOLDER")){
                listeDetteNonSolde.add(dette);
            };
        }
        return listeDetteNonSolde;
    }
 
    
}
