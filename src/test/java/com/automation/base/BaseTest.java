package com.automation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static WebDriver driver;
    public Logger logger;
    @BeforeClass
    public void setup() {
        logger = LogManager.getLogger(this.getClass());


    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
