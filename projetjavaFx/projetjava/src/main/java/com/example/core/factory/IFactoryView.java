package com.example.core.factory;

import com.example.views.Interfaces.IArticleView;
import com.example.views.Interfaces.IClientView;
import com.example.views.Interfaces.IDetteView;
import com.example.views.Interfaces.IPaiementView;
import com.example.views.Interfaces.IUserView;


public interface IFactoryView {
    IArticleView getInstanceArticleView();
    IPaiementView getInstancePaiementView();
    IClientView getInstanceClientView();
    IUserView getInstanceUserView();
    IDetteView getInstanceDette();
}
