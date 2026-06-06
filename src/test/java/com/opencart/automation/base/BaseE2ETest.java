package com.opencart.automation.base;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseE2ETest extends BaseTest {
    @Override
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        super.setUp(browser);   //gọi logic từ BaseTest
        logger.info("***** Starting E2E Test *****");
    }
    @Override
    @AfterClass
    public void tearDown() {
        logger.info("***** Finished E2E Test *****");
        super.tearDown();  //gọi logic từ BaseTest
    }
}
