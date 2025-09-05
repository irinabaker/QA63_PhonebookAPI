package com.phonebook.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {

    private static final String PROP_FILE = "/data.properties";

    public PropertiesLoader() {
    }

    public static String loadProperty(String name) {
        Properties properties = new Properties();

        try {
            properties.load(PropertiesLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";
        if (name != null) {
            value = properties.getProperty(name);
        }
        return value;
    }

}
