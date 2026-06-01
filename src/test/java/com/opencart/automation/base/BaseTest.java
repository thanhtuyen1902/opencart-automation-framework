package com.opencart.automation.base;

import com.opencart.automation.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    public Logger logger;
    //sửa thành beforemethod
    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        logger = LogManager.getLogger(this.getClass());
        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getBrowser();
        }
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getUrl());

        logger.info("======================================================");
        logger.info("TEST STARTED");
        logger.info("Browser: {}", browser);
        logger.info("URL: {}", ConfigReader.getUrl());
        logger.info("======================================================");
    }

    @AfterMethod
    public void tearDown() {
        logger.info("=== TEST COMPLETED. Closing browser ===");
        if (driver != null) {
            DriverFactory.quitDriver();
            driver = null;
        }

    }

    //Capture screen when test failed
    public static String captureScreenshot(String testName) {
        //lấy driver gốc
        WebDriver originalDriver = ((com.epam.healenium.SelfHealingDriver)DriverFactory.getDriver()).getDelegate();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //Define image name
        String screenshotName = testName + "_" + timeStamp + ".png";
        String targetPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
        //Capture throw interface TakesScreenshot of Selenium
        TakesScreenshot ts = (TakesScreenshot) originalDriver;
        // File tạm
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(targetPath);
        //Copy và ghi đè vào vị trí file thực tế
        try {
            FileUtils.copyFile(sourceFile, targetFile);
        }catch (IOException e) {
            return null;
        }
        return targetPath;
    }
}
