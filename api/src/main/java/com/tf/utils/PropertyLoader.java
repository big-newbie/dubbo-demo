package com.tf.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by keefe
 * 2018-12-10
 */
public final class PropertyLoader {

    private static final String DUBBO_REGISTRY_ADDRESS = "dubbo.registry.address";

    private PropertyLoader() {}

    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static String getRegistry() {

        return map.computeIfAbsent(DUBBO_REGISTRY_ADDRESS, k -> {
            Properties properties = new Properties();
            String name = "connection.properties";
//        properties.load(PropertyLoader.class.getResourceAsStream("/connection.properties"));
            try {
                properties.load(PropertyLoader.class.getClassLoader().getResourceAsStream(name));
            } catch (IOException e) {
                throw new RuntimeException();
            }
            return properties.getProperty(DUBBO_REGISTRY_ADDRESS);
        });
    }
}