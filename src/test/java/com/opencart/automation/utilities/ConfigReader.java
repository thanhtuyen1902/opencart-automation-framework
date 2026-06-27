package com.opencart.automation.utilities;

import java.io.FileInputStream;
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
    //Application URL
    public static String getUrl(){
        return getProperty("base_url");
    }
    // Login credentials
    public static String getEmail() {
        return getProperty("email");
    }
    public static String getPassword() {
        return getProperty("password");
    }
    // Implicit Wait timeout

    //Hạ tầng chạy test (local/remote)
    public static String getExecutionMode() {
//        return getProperty("execution_env").toLowerCase().trim();
        //ưu tiên lấy giá trị từ cmdline nếu có, nếu không có thì lấy từ config.properties
        return System.getProperty(
                        "execution_env",
                        getProperty("execution_env")).toLowerCase().trim();
    }

    public static String getGridURL() {
        return getProperty("grid_url");
    }

}
