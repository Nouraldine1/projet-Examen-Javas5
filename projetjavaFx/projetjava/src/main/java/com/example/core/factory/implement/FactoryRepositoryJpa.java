package com.example.core.factory.implement;

import com.example.core.factory.IFactoryRepository;
import com.example.core.repositories.jpa.ArticleRepositoryJpa;
import com.example.core.repositories.jpa.ClientRepositoryJpa;
import com.example.core.repositories.jpa.DetteRepositoryJpa;
import com.example.core.repositories.jpa.PaiementRepositoryJpa;
import com.example.core.repositories.jpa.UserRepositoryJpa;
import com.example.core.repositories.list.DemandeDetteRepository;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repositories.list.interfaces.IUserRepository;

public class FactoryRepositoryJpa implements IFactoryRepository{
    private IArticleRepository articleRepository;
    private IClientRepository clientRepository;
    private IDetteRepository detteRepository;
    private IUserRepository userRepository;
    private IPaiementRepository paiementRepository;
    private IDemandeDetteRepository demandeDetteRepository;

    @Override
    public IArticleRepository getInstanceArticleRepository() {
        return articleRepository == null ? new ArticleRepositoryJpa() : articleRepository;
    }

    @Override
    public IClientRepository getInstanceClientRepository() {
        return clientRepository == null ? new ClientRepositoryJpa() : clientRepository;
    }

    @Override
    public IDetteRepository getInstanceDetteRepository() {
        return detteRepository == null ? new DetteRepositoryJpa() : detteRepository;
    }


    @Override
    public IPaiementRepository getInstacePaiementRepository() {
        return paiementRepository == null ? new PaiementRepositoryJpa() : paiementRepository;
    }

    @Override
    public IUserRepository getInstanceUserRepository() {
        return userRepository == null ? new UserRepositoryJpa() : userRepository;
    }

    @Override
    public IDemandeDetteRepository getInstaceDemandeDetteRepository() {
        return demandeDetteRepository == null ? new DemandeDetteRepository() : demandeDetteRepository;
    }

    
}
