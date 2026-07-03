package com.opencart.automation.core;

import com.epam.healenium.SelfHealingDriver;
import com.opencart.automation.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    //mỗi thread sẽ có một webdriver riêng biệt
    //dùng để hỗ trợ parallel testing, selfhealing
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    /**
     * Khởi tạo driver theo browser và mode (local/remote)
     *
     */
    public static void initDriver(String browser) {
//        String browserName = ConfigReader.getBrowser().toLowerCase().trim();
        String executionMode = ConfigReader.getExecutionMode();
        WebDriver webDriver;
        try {
            switch(executionMode) {
                case "remote":
                    webDriver = createRemoteDriver(browser);
                    break;
                default:
                    webDriver = createLocalDriver(browser);
                    break;
            }
            //bọc với SelfHealingDriver rồi set vào ThreadLocal
            try {
                SelfHealingDriver healingDriver = SelfHealingDriver.create(webDriver);
                driver.set(healingDriver);
                logger.info("Create SelfHealingDriver successfully for browser: {}", browser);
            }catch (Exception e) {
                System.err.println("Error when create SelfHealingDriver: " + e.getMessage());
                driver.set(webDriver);
            }

            WebDriver currentDriver = driver.get();
            //có thể tách thành method riêng configureDriver()
            currentDriver.manage().deleteAllCookies();
            currentDriver.manage().window().maximize();
            currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }catch(Exception e) {
            System.err.println("Error when create driver: " + e.getMessage());
            throw new RuntimeException("Cannot create WebDriver", e);
        }


    }

    //Trả về driver của thread hiện tại
    public static WebDriver getDriver() {
        //trả về driver cho nơi gọi (thường basetest)
        return driver.get();
    }

    //Đóng driver và dọn dẹp threadlocal
    public static void quitDriver(){
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }

    }

    //Tạo driver cho local execution
    private static WebDriver createLocalDriver(String browser) {

        switch(browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions());
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver(getEdgeOptions());
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(getFirefoxOptions());
            default:
                System.err.println("Browser không hỗ trợ: " + browser + ". Sử dụng chrome là browser mặc định");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions());

        }
    }
    //Tạo driver cho remote execution (Selenium Grid)
    private static WebDriver createRemoteDriver(String browser) {
        String gridURL = ConfigReader.getGridURL();
        try {
            switch(browser.toLowerCase()) {
                case "chrome":
                    URL url = null;
                    try {
                        url = new URI(gridURL).toURL();
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                    return new RemoteWebDriver(url, getChromeOptions());
                case "edge":
                    return new RemoteWebDriver(new URL(gridURL), getEdgeOptions());
                case "firefox":
                    return new RemoteWebDriver(new URL(gridURL), getFirefoxOptions());
                default:
                    System.err.println("Browser không hỗ trợ: " + browser + ". Sử dụng chrome là browser mặc định");
                    return new RemoteWebDriver(new URL(gridURL), getChromeOptions());
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }



    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
//        chromeOptions.addArguments("--headless");
        return chromeOptions;
    }
    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
//        firefoxOptions.addArguments("--headless");
        return firefoxOptions;
    }
    private static EdgeOptions getEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setAcceptInsecureCerts(true);
//        edgeOptions.addArguments("--headless");
        return edgeOptions;
    }
}
