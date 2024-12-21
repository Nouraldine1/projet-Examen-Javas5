package com.example.core.factory;

import com.example.core.services.interfaces.IArticleService;
import com.example.core.services.interfaces.IClientService;
import com.example.core.services.interfaces.IDemandeDetteService;
import com.example.core.services.interfaces.IDetteService;
import com.example.core.services.interfaces.IPaiementService;
import com.example.core.services.interfaces.IUserService;


public interface IFactoryService {
    IArticleService getInstanceArticleService();
    IPaiementService getInstancePaiementService();
    IClientService getInstanceClientService();
    IDetteService getInstanceDetteService();
    IUserService getInstanceUserService();
    IDemandeDetteService getInstaDemandeDetteService();
}
