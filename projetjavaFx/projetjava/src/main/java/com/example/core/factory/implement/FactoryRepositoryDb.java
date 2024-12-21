package com.example.core.factory.implement;

import com.example.core.factory.IFactoryRepository;
import com.example.core.repositories.db.ArticleRepositoryDb;
import com.example.core.repositories.db.ClientRepositoryDb;
import com.example.core.repositories.db.DetteRepositoryDb;
import com.example.core.repositories.db.PaiementRepositoryDb;
import com.example.core.repositories.db.UserRepositoryDb;
import com.example.core.repositories.list.DemandeDetteRepository;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repositories.list.interfaces.IUserRepository;


public class FactoryRepositoryDb implements IFactoryRepository{
    private IArticleRepository articleRepositoryDb;
    private IClientRepository clientRepositoryDb;
    private IDetteRepository detteRepositoryDb;
    private IUserRepository userRepositoryDb;
    private IPaiementRepository paiementRepositoryDb;
    private IDemandeDetteRepository demandeDetteRepository;

    @Override
    public IArticleRepository getInstanceArticleRepository() {
        return articleRepositoryDb == null ? new ArticleRepositoryDb() : articleRepositoryDb;
    }

    @Override
    public IClientRepository getInstanceClientRepository() {
        return clientRepositoryDb == null ? new ClientRepositoryDb() : clientRepositoryDb;
    }

    @Override
    public IDetteRepository getInstanceDetteRepository() {
        return detteRepositoryDb == null ? new DetteRepositoryDb() : detteRepositoryDb;
    }


    @Override
    public IPaiementRepository getInstacePaiementRepository() {
        return paiementRepositoryDb == null ? new PaiementRepositoryDb() : paiementRepositoryDb;
    }

    @Override
    public IUserRepository getInstanceUserRepository() {
        return userRepositoryDb == null ? new UserRepositoryDb() : userRepositoryDb;
    }

    @Override
    public IDemandeDetteRepository getInstaceDemandeDetteRepository() {
        return demandeDetteRepository == null ? new DemandeDetteRepository() : demandeDetteRepository;
    }
}
