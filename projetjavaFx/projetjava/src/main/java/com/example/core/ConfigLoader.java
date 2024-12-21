package com.example.core;


import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;




public class ConfigLoader {

    private static Map<String, Object> config;

    static {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("application.yaml")) {
            Yaml yaml = new Yaml();
            config = yaml.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getRepositoryType() {
        return (String) config.get("app.repository");
    }

    public static String getPersistenceUnit() {
        return (String) config.get("app.persistence-unit");
    }
}
