package com.ism.database;

import java.util.Map;
import com.ism.config.ConfigLoader;
import com.ism.config.impl.ConfigLoaderImpl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DatabaseConfig {
    private String url;
    private String username;
    private String password;
    private String driver;
    private String DefaultDatabase;

    public DatabaseConfig() {
        loadDatabaseConfig();
    }
    private void loadDatabaseConfig() {
        ConfigLoader configLoader = new ConfigLoaderImpl();
        Map<String, Object> config = configLoader.loadYaml();
    
        @SuppressWarnings("unchecked")
        String defaultDb = (String) ((Map<String, Object>) config.get("database")).get("default");
        System.out.println("Base de données par défaut : " + defaultDb);
    
        @SuppressWarnings("unchecked")
        Map<String, String> dbConfig = (Map<String, String>) ((Map<String, Object>) config.get("database")).get(defaultDb);
    
        if (dbConfig == null) {
            throw new RuntimeException("Configuration pour '" + defaultDb + "' introuvable dans le fichier YAML !");
        }
    
        this.url = dbConfig.get("url");
        this.username = dbConfig.get("username");
        this.password = dbConfig.get("password");
        this.driver = dbConfig.get("driver");
    
        System.out.println("Driver chargé : " + this.driver);
        System.out.println("URL : " + this.url);
        System.out.println("Utilisateur : " + this.username);
    }
    
    
}
