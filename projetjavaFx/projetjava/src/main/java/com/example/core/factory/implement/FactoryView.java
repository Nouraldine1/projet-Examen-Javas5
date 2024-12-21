package com.example.core.factory.implement;

import com.example.core.factory.IFactoryRepository;
import com.example.core.factory.IFactoryService;
import com.example.core.factory.IFactoryView;
import com.example.views.ArticleView;
import com.example.views.ClientView;
import com.example.views.DetteView;
import com.example.views.Interfaces.IArticleView;
import com.example.views.Interfaces.IClientView;
import com.example.views.Interfaces.IDetteView;
import com.example.views.Interfaces.IPaiementView;
import com.example.views.Interfaces.IUserView;
import com.example.views.PaiementView;
import com.example.views.UserView;



public class FactoryView implements IFactoryView {
    private IArticleView articleView;
    private IClientView clientView;
    private IPaiementView paiementView;
    private IDetteView detteView;
    private IUserView userView;
    private IFactoryService factoryService;
    private IFactoryRepository factoryRepository;

    public FactoryView(IFactoryService factoryService,IFactoryRepository factoryRepository) {
        this.factoryService = factoryService;
        this.factoryRepository = factoryRepository;
    }

    @Override
    public IArticleView getInstanceArticleView() {
        return articleView == null ? new ArticleView(factoryService.getInstanceArticleService()) : articleView;
    }

    @Override
    public IClientView getInstanceClientView() {
        return clientView == null ? new ClientView(factoryService.getInstanceClientService()) : clientView;
    }
    @Override
    public IPaiementView getInstancePaiementView() {
        return paiementView == null ? new PaiementView(factoryService.getInstancePaiementService(),factoryService.getInstanceDetteService(),
                                                       factoryRepository.getInstanceDetteRepository()) : paiementView;
    }

    @Override
public IDetteView getInstanceDette() {
    return detteView == null ? new DetteView(factoryService.getInstanceDetteService(), 
                                             factoryService.getInstanceClientService(),
                                             factoryService.getInstanceArticleService(), 
                                             factoryService.getInstancePaiementService(),
                                             new PaiementView(factoryService.getInstancePaiementService(),
                                             factoryService.getInstanceDetteService(),
                                             factoryRepository.getInstanceDetteRepository()),factoryService.getInstaDemandeDetteService()) 
                             : detteView;
}

    @Override
    public IUserView getInstanceUserView() {
        return userView == null ? new UserView(factoryService.getInstanceUserService()) : userView;
    }
}
