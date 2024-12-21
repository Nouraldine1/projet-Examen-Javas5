package com.ism.config.impl;

import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.ism.config.ConfigLoader;

public class ConfigLoaderImpl implements ConfigLoader {

    private String path="config.yaml";

    @Override
    public Map<String, Object> loadYaml() {
      return  this.loadYaml(path);
    }

    @Override
    public Map<String, Object> loadYaml(String path) {
        Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream(path);
            return yaml.load(inputStream);
    }
    
 
}
