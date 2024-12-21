package com.example.core.factory;

public interface IFactory {
    IFactoryRepository getFactoryRepository();
    IFactoryService getFactoryService();
    IFactoryView getFactoryView();
}
