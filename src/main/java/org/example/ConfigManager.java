package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private final Properties configProps;

    public ConfigManager() throws IOException {
        configProps = new Properties();
        loadProperties();
    }

    //reading the properties from config.properties
    private void loadProperties() throws IOException {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            configProps.load(input);
        }
    }

    public String getApiKey() {
        return configProps.getProperty("api.key");
    }

    public String getApiUrl() {
        return configProps.getProperty("api.url");
    }

    public String model() {
        return configProps.getProperty("model");
    }

}