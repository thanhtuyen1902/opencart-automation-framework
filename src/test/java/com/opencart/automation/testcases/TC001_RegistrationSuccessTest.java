package com.opencart.automation.testcases;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.models.User;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.MyAccountPage;
import com.opencart.automation.pages.RegistrationPage;
import com.opencart.automation.pages.SuccessRegisterPage;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC001_RegistrationSuccessTest extends BaseTest {
    @Test
    public void verifyRegisterSuccess() {
        logger.info("***** Starting TC001_RegistrationSuccessTest ******");
        try {
            HomePage hp = new HomePage(driver);
            RegistrationPage rp = new RegistrationPage(driver);
            SuccessRegisterPage success_rp = new SuccessRegisterPage(driver);
            MyAccountPage acp = new MyAccountPage(driver);
            User user = TestDataGenerator.generateUserWithDefaultPwd();
            SoftAssert softAssert = new SoftAssert();
            logger.info("Click on My Account dropdown");
            hp.clickMyAccount();
            logger.info("Click on Register link to open RegisterPage");
            hp.clickRegisterPage();
            logger.info("Providing user info...");
            rp.fillRegisterForm(user);
            logger.info("Setting privacy policy");
            rp.setAgreePolicy();
            logger.info("Click on Continue button");
            rp.clickContinue();
            logger.info("Validate expected msg");
            softAssert.assertTrue(success_rp.getSuccessTitle().contains("Your Account Has Been Created!"), "Success message is showed");
            softAssert.assertTrue(success_rp.isSuccessPageDisplayed(), "SuccessPage is displayed");
            logger.info("Validate redirection to MyAccountPage");
            success_rp.clickContinue();
            softAssert.assertTrue(acp.isMyAccountPageExists(), "MyAccountPage is displayed!");
//            logger.info("Register new user successfully!");
            logger.info("Registering user: {}", user);
        }catch (Exception e) {
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("***** Finished TC001_RegistrationSuccessTest ******");



    }
}
