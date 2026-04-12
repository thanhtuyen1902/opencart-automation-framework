package com.opencart.automation.testcases;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.LoginPage;
import com.opencart.automation.pages.MyAccountPage;
import com.opencart.automation.utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC002_LoginDDT extends BaseTest {
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,
    description = "Data Driven Test - Verify login validation errors")
    public void verifyLoginWithError(String email, String password) {
        logger.info("***** Starting TC002_LoginDDT ******");
        try {
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);
            MyAccountPage acp = new MyAccountPage(driver);
            SoftAssert softAssert = new SoftAssert();
            logger.info("Click on My Account dropdown");
            hp.clickMyAccount();
            logger.info("Click on Login link to open LoginPage");
            hp.clickLoginPage();
            logger.info("Enter email");
            lp.setEmailAddress(email);
            logger.info("Enter password");
            lp.setPassword(password);
            logger.info("Click button Login");
            lp.clickLogin();
            logger.info("Login user: email={}, password={}", email, password);
            logger.info("Validating login successfully!");
            if (acp.isMyAccountPageExists()) {
                Assert.assertTrue(true);
            }else {
                logger.error("Test failed");
                logger.debug("Debug logs..");
                Assert.fail();
            }
            acp.clickLogout();
        }catch(Exception e) {
            Assert.fail();
        }

        logger.info("***** Finished TC002_LoginDDT ******");
    }
}
