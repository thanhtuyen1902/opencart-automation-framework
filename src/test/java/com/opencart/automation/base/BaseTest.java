package com.opencart.automation.base;

import com.opencart.automation.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    public Logger logger;
    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());
        //------------------------------------------------
        //Khởi tạo driver
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        //Mở Url từ ConfigReader
//        driver.get(ConfigReader.getUrl());	//reading url from properties file
//        driver.manage().window().maximize();
        //------------------------------------------------
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getUrl());

        logger.info("======================================================");
        logger.info("TEST STARTED");
        logger.info("Browser: {}", ConfigReader.getBrowser());
        logger.info("URL: {}", ConfigReader.getUrl());
        logger.info("======================================================");
    }

    @AfterClass
    public void tearDown() {
        logger.info("=== TEST COMPLETED. Closing browser ===");
//        driver.quit();
        if (driver != null) {
            DriverFactory.quitDriver();
            driver = null;
        }

    }
}
