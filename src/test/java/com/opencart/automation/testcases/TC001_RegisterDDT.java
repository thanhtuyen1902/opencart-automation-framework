package com.opencart.automation.testcases;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.models.User;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.RegistrationPage;
import com.opencart.automation.pages.SuccessRegisterPage;
import com.opencart.automation.utilities.DataProviders;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC001_RegisterDDT extends BaseTest {
    @Test(dataProvider = "negativeRegistrationData", dataProviderClass = DataProviders.class,
    description = "Data Driven Test - Verify registration validation errors")
    public void verifyRegistrationWithError(String testcaseId,
                                            String firstName,
                                            String lastName,
                                            String email,
                                            String telephone,
                                            String password,
                                            String confirmPassword,
                                            String subscribeNewsletter,
                                            String agreeTerms,
                                            String expected) {

        logger.info("***** Starting TC001_RegisterDDT ******");
        try {
            HomePage hp = new HomePage(driver);
            RegistrationPage rp = new RegistrationPage(driver);
            SuccessRegisterPage success_rp = new SuccessRegisterPage(driver);
            SoftAssert softAssert = new SoftAssert();
            logger.info("Click on My Account dropdown");
            hp.clickMyAccount();
            logger.info("Click on Register link to open RegisterPage");
            hp.clickRegisterPage();
            logger.info("Providing user info...");
            rp.registerNewUser(firstName, lastName, email, telephone, password, confirmPassword, subscribeNewsletter, agreeTerms);
            logger.info("Click on Continue button");
            rp.clickContinue();
            logger.info("Validate expected msg");
//            softAssert.assertTrue(rp.getConfirmationMsg().contains(expected), "Error: First Name must be between 1 and 32 characters!");
            if (firstName.isEmpty()) {
                logger.info("Validate First Name field");
                softAssert.assertTrue(rp.isFirstNameErrorMsgDisplayed(expected), "Error: First Name must be between 1 and 32 characters!");
            }
            if (lastName.isEmpty()) {
                logger.info("Validate Last Name field");
                softAssert.assertTrue(rp.isLastNameErrorMsgDisplayed(expected), "Last Name must be between 1 and 32 characters!");
            }
            logger.info("Registering user: firstName={}, lastName={}",firstName, lastName);
        }catch (Exception e) {
            logger.error("Test failed");
            Assert.fail();
        }

        logger.info("***** Finished TC001_RegisterDDT ******");

    }
}
