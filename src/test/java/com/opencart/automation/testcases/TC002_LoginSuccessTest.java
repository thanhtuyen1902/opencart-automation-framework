package com.opencart.automation.testcases;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginSuccessTest extends BaseTest {
    @Test
    public void verifyLoginSuccess() {
        try {
            logger.info("Login test");
            HomePage hp = new HomePage(driver);
            LoginPage lp = new LoginPage(driver);

            hp.clickMyAccount();
            hp.clickLoginPage();
            lp.setEmailAddress("thuthu22a@gmail.com");
            lp.setPassword("Aa123456");
            lp.clickLogin();

        }catch (Exception e) {
            Assert.fail();
        }
    }

}
