package com.example.core.factory.implement;

import com.example.core.factory.IFactory;
import com.example.core.factory.IFactoryRepository;
import com.example.core.factory.IFactoryService;
import com.example.core.factory.IFactoryView;


public class Factory implements IFactory {
    @Override
    public IFactoryRepository getFactoryRepository() {
        return new FactoryRepository();
    }

    @Override
    public IFactoryService getFactoryService() {
        return new FactoryService(getFactoryRepository());
    }

    @Override
    public IFactoryView getFactoryView() {
        return new FactoryView(getFactoryService(),getFactoryRepository());
    }    
}
