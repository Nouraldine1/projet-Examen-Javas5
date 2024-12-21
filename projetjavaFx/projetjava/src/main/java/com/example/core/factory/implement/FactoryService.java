package com.example.core.factory.implement;

import com.example.core.factory.IFactoryRepository;
import com.example.core.factory.IFactoryService;
import com.example.core.services.ArticleService;
import com.example.core.services.ClientService;
import com.example.core.services.DemandeDetteService;
import com.example.core.services.DetteService;
import com.example.core.services.PaiementService;
import com.example.core.services.UserService;
import com.example.core.services.interfaces.IArticleService;
import com.example.core.services.interfaces.IClientService;
import com.example.core.services.interfaces.IDemandeDetteService;
import com.example.core.services.interfaces.IDetteService;
import com.example.core.services.interfaces.IPaiementService;
import com.example.core.services.interfaces.IUserService;




public class FactoryService implements IFactoryService {
    private IArticleService articleService;
    private IClientService clientService;
    private IDetteService detteService;
    private IUserService userRepository;
    private IPaiementService paiementService;
    private IDemandeDetteService demandeDetteService;
    private IFactoryRepository factoryRepository;

    public FactoryService(IFactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @Override
    public IArticleService getInstanceArticleService() {
        return articleService == null ? new ArticleService(factoryRepository.getInstanceArticleRepository()) : articleService;
    }

    @Override
    public IClientService getInstanceClientService() {
        return clientService == null ? new ClientService(factoryRepository.getInstanceClientRepository()) : clientService;
    }

    @Override
    public IDetteService getInstanceDetteService() {
        return detteService == null ? new DetteService(factoryRepository.getInstanceDetteRepository()) : detteService;
    }

    @Override
    public IUserService getInstanceUserService() {
        return userRepository == null ? new UserService(factoryRepository.getInstanceUserRepository()) : userRepository;
    }

    @Override
    public IPaiementService getInstancePaiementService() {
        return paiementService==null ? new PaiementService(factoryRepository.getInstacePaiementRepository()) : paiementService;
    }

   @Override
    public IDemandeDetteService getInstaDemandeDetteService() {
        return demandeDetteService==null ? new DemandeDetteService(factoryRepository.getInstaceDemandeDetteRepository()):demandeDetteService;
    }
}
