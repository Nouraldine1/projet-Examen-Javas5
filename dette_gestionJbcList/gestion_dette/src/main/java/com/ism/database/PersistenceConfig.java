package com.ism.database;

import java.util.Map;
import com.ism.config.impl.*;
import lombok.*;

@Getter
public class PersistenceConfig {
    private String repositoryType;
    public PersistenceConfig() {
        loadPersistenceConfig();
    }

    @SuppressWarnings("unchecked")
    private void loadPersistenceConfig(){
        ConfigLoaderImpl configLoader = new ConfigLoaderImpl();
        Map<String, Object> config = configLoader.loadYaml();  
        
        Map<String, Object> persistenceConfig = (Map<String, Object>) config.get("persistence");
        Map<String, String> repositoryConfig = (Map<String, String>) persistenceConfig.get("repository");

        this.repositoryType = repositoryConfig.get("type");
    }

    
}
