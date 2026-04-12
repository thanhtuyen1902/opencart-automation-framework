package com.opencart.automation.testcases;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.LoginPage;
import com.opencart.automation.pages.MyAccountPage;
import com.opencart.automation.utilities.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginSuccessTest extends BaseTest {
    @Test
    public void verifyLoginSuccess() {
        logger.info("***** Starting TC002_LoginSuccessTest ******");
        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage acp = new MyAccountPage(driver);
            logger.info("Click on My Account dropdown");
            hp.clickMyAccount();
            logger.info("Click on Login link to open LoginPage");
            hp.clickLoginPage();
            //Get data from config.properties
            logger.info("Enter Email");
            lp.setEmailAddress(ConfigReader.getProperty("email"));
            logger.info("Enter Password");
            lp.setPassword(ConfigReader.getProperty("password"));
            logger.info("Click button Login");
            lp.clickLogin();
            logger.info("Validating login successfully!");
            if (acp.isMyAccountPageExists()) {
                Assert.assertTrue(true);
            }else {
                logger.error("Test failed");
                logger.debug("Debug logs..");
                Assert.fail();
            }

        }catch (Exception e) {
            Assert.fail();
        }
        logger.info("***** Finished TC002_LoginSuccessTest ******");
    }

}
