package com.example.core.factory.implement;

import com.example.core.factory.IFactoryRepository;
import com.example.core.repositories.list.ArticleRepository;
import com.example.core.repositories.list.ClientRepository;
import com.example.core.repositories.list.DemandeDetteRepository;
import com.example.core.repositories.list.DetteRepository;
import com.example.core.repositories.list.PaiementRepository;
import com.example.core.repositories.list.UserRepository;
import com.example.core.repositories.list.interfaces.IArticleRepository;
import com.example.core.repositories.list.interfaces.IClientRepository;
import com.example.core.repositories.list.interfaces.IDemandeDetteRepository;
import com.example.core.repositories.list.interfaces.IDetteRepository;
import com.example.core.repositories.list.interfaces.IPaiementRepository;
import com.example.core.repositories.list.interfaces.IUserRepository;


public class FactoryRepository implements IFactoryRepository {
    private IArticleRepository articleRepository;
    private IClientRepository clientRepository;
    private IDetteRepository detteRepository;
    private IUserRepository userRepository;
    private IPaiementRepository paiementRepository;
    private IDemandeDetteRepository demandeDetteRepository;

    @Override
    public IArticleRepository getInstanceArticleRepository() {
        return articleRepository == null ? new ArticleRepository() : articleRepository;
    }

    @Override
    public IClientRepository getInstanceClientRepository() {
        return clientRepository == null ? new ClientRepository() : clientRepository;
    }

    @Override
    public IDetteRepository getInstanceDetteRepository() {
        return detteRepository == null ? new DetteRepository() : detteRepository;
    }

    @Override
    public IUserRepository getInstanceUserRepository() {
        return userRepository == null ? new UserRepository() : userRepository;
    }
    
    @Override
    public IPaiementRepository getInstacePaiementRepository() {
        return paiementRepository == null ? new PaiementRepository() : paiementRepository;
    }

    @Override
    public IDemandeDetteRepository getInstaceDemandeDetteRepository() {
        return demandeDetteRepository == null ? new DemandeDetteRepository() : demandeDetteRepository;
    }
}
