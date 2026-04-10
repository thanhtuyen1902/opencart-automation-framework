package com.opencart.automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties p;
//    Constructor khởi tạo object
    static {
        try {
            String configPath = System.getProperty("user.dir")
                    + "/src/test/resources/config.properties";
            FileInputStream fis = new FileInputStream(configPath);
            p = new Properties();
            p.load(fis);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    public static String getProperty(String key) {
        String value = p.getProperty(key);
        if (value==null || value.trim().isEmpty()) {
            System.err.println("Warning: Property key" + key + "not found in config.properties");
            return "";
        }
        return value.trim();
    }
    //GET METHOD
    public static String getBrowser() {
        return getProperty("browser");
    }
    public static String getUrl(){
        return getProperty("url");
    }
    public static String getUsername() {
        return getProperty("username");
    }
    public static String getPassword() {
        return getProperty("password");
    }


}
