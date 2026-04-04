package com.automation.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties p;
//    Constructor khởi tạo object
    public ConfigReader() {
        try {
            FileInputStream fis = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/config.properties");
            p = new Properties();
            p.load(fis);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    //GET METHOD
    public static String getBrowser() {
        return p.getProperty("browser");
    }
    public static String getUrl(){
        return p.getProperty("url");
    }
    public static String getUsername() {
        return p.getProperty("username");
    }
    public static String getPassword() {
        return p.getProperty("password");
    }


}
