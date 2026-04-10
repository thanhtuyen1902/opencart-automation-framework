package com.opencart.automation.base;

import com.opencart.automation.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {
    //mỗi thread sẽ có một webdriver riêng biệt
    //dùng để hỗ trợ parallel testing
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        //trả về driver cho nơi gọi (thường basetest)
        return driver.get();
    }

    private static void initializeDriver() {
        String browserName = ConfigReader.getBrowser().toLowerCase().trim();
        switch(browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                //Tạo ChromeDriver và lưu vào ThreadLocal
                driver.set(new ChromeDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            default:
                System.err.println("Browser không hỗ trợ: " + browserName + ". Sử dụng chrome là browser mặc định");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
        }
        //lấy driver ra từ ThreadLocal
        WebDriver webDriver = driver.get();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    //đóng web driver và dọn dẹp threadlocal
    public static void quitDriver(){
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

    }
}
