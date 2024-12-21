package com.ism.config;

import java.util.Map;

public interface ConfigLoader {

    Map<String, Object> loadYaml();
    Map<String, Object> loadYaml(String path);
}
