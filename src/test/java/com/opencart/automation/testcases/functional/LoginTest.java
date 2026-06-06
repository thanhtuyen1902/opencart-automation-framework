package com.opencart.automation.testcases.functional;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.LoginPage;
import com.opencart.automation.pages.MyAccountPage;
import com.opencart.automation.utilities.ConfigReader;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Test(groups = {"Login"})
public class LoginTest extends BaseTest {
    private LoginPage lp;
    private MyAccountPage acp;
    private SoftAssert softAssert;
    private HomePage hp;

    @BeforeMethod
    public void navigateToLoginPage() {
        logger.info("***** Starting LoginTest *****");
        try {
            hp = new HomePage(driver);
            softAssert = new SoftAssert();
            logger.info("Click on My Account dropdown");
            hp.clickMyAccount();
            logger.info("Click on Login link to open LoginPage");
            hp.clickLoginPage();
            lp = new LoginPage(driver);
        }catch (Exception e) {
            Assert.fail();
        }
    }
    @Test(description = "LG_01 - Đăng nhập thành công với tài khoản hợp lệ")
    public void testLoginSuccess() {
        logger.info("***** Starting LG_01 - Đăng nhập thành công với tài khoản hợp lệ ******");
        try {
            //Get data from config.properties
            logger.info("Step 1: Enter Email");
            lp.setEmailAddress(ConfigReader.getProperty("email"));
            logger.info("Step 2: Enter Password");
            lp.setPassword(ConfigReader.getProperty("password"));
            logger.info("Step 3: Click button Login");
            lp.clickLogin();
            acp = new MyAccountPage(driver);
            logger.info("Step 4: Validating login success");
            if (acp.isMyAccountPageExists()) {
                Assert.assertTrue(true, "MyAccountPage should be displayed");
            }else {
                logger.error("Test failed");
                logger.debug("Debug logs..");
                Assert.fail();
            }
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished LG_01 - Đăng nhập thành công với tài khoản hợp lệ ******");
    }
    @Test(description = "LG_02 - Đăng nhập thất bại với mật khẩu sai")
    public void testLoginFailedWithWrongPassword() {
        logger.info("***** Starting LG_02 - Đăng nhập thất bại với mật khẩu sai ******");
        try {
            String email = ConfigReader.getEmail();
            String password = ConfigReader.getPassword() + "123";
            logger.info("Step 1: Enter email");
            lp.setEmailAddress(email);
            logger.info("Step 2: Enter password");
            lp.setPassword(password);
            logger.info("Login user: email={}, password={}", email, password);
            logger.info("Step 3: Click button Login");
            lp.clickLogin();
            logger.info("Step 4: Verify alert warning message");
            Assert.assertTrue(lp.isLoginFailedMessageDisplayed("Warning: No match for E-Mail Address and/or Password."), "Warning message should be displayed");
            logger.info("TEST PASSED");
        }catch(Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }

        logger.info("***** Finished LG_02 - Đăng nhập thất bại với mật khẩu sai ******");
    }
    @Test(description = "LG_03 - Đăng nhập thất bại với Email không tồn tại")
    public void testLoginFailedWithInvalidEmail() {
        logger.info("***** Starting LG_03 - Đăng nhập thất bại với Email không tồn tại ******");
        try {
            String email = TestDataGenerator.generateRandomEmail();
            logger.info("Step 1: Enter email");
            lp.setEmailAddress(email);
            logger.info("Step 2: Enter password");
            lp.setPassword("123456");
            logger.info("Login user: email={}", email);
            logger.info("Step 3: Click button Login");
            lp.clickLogin();
            logger.info("Step 4: Verify alert warning message");
            Assert.assertTrue(lp.isLoginFailedMessageDisplayed("Warning: No match for E-Mail Address and/or Password."), "Warning message should be displayed");
            logger.info("TEST PASSED");
        }catch(Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }

        logger.info("***** Finished LG_03 - Đăng nhập thất bại với Email không tồn tại ******");
    }
//    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,
//            description = "[Data Driven Test] Đăng nhập thất bại với các dữ liệu không hợp lệ")
//    public void testLoginFailed(String testcaseId, String expectedErrorField, String email, String password) {
//        logger.info("***** Starting LG_02, LG_03 - Đăng nhập thất bại với các dữ liệu không hợp lệ ******");
//        logger.info("***** [Data Driven Test] testcaseId={} - Missing {} ******", testcaseId, expectedErrorField);
//        try {
//            logger.info("Step 1: Enter email");
//            lp.setEmailAddress(email);
//            logger.info("Step 2: Enter password");
//            lp.setPassword(password);
//            logger.info("Step 3: Click button Login");
//            lp.clickLogin();
//            logger.info("Login user: email={}, password={}", email, password);
//            logger.info("Step 4: Verify alert warning message");
//            softAssert.assertTrue(lp.isLoginFailedMessageDisplayed("Warning: No match for E-Mail Address and/or Password."), "Warning message should be displayed");
//            softAssert.assertAll();
//            logger.info("TEST PASSED");
//        }catch(Exception e) {
//            logger.error("TEST FAILED: ", e);
//            Assert.fail("Test failed with exception: " + e.getMessage());
//        }
//
//        logger.info("***** Finished LG_02, LG_03 - Đăng nhập thất bại với các dữ liệu không hợp lệ ******");
//    }

//    @Test(description = "LG_04 - Đăng nhập thất bại khi nhập sai quá số lần cho phép")
//    public void testLoginExceedAttempts() {
//        logger.info("***** Starting LG_04 - Đăng nhập thất bại khi nhập sai quá số lần cho phép ******");
//        try {
//            logger.info("Login");
//            lp.login("ngocanh@gmail.com", "Aa123456");
//            acp = new MyAccountPage(driver);
//            acp.clickLogout();
//            acp.clickContinueLogout();
//            hp = new HomePage(driver);
//            hp.clickMyAccount();
//            hp.clickLoginPage();
//            String email = TestDataGenerator.generateRandomEmail();
//            //cần confirm thêm (không ổn định về spec)
//            for (int i = 0; i < 6; i++) {
//                Thread.sleep(10);
//                LoginPage lp = new LoginPage(driver);
//                logger.info("Login attempt #{}", i+1);
//                logger.info("Step 1: Enter Email");
//                lp.setEmailAddress(email);
//                logger.info("Step 2: Enter Password");
//                lp.setPassword("Aa123456");
//                logger.info("Step 3: Click button Login");
//                lp.clickLogin();
//                logger.info("Verify alert warning message");
//                softAssert.assertTrue(lp.isLoginFailedMessageDisplayed(
//                                "Warning: No match for E-Mail Address and/or Password."),
//                        "Warning message should be displayed correctly");
//            }
//            LoginPage lp = new LoginPage(driver);
//            logger.info("Step 4: Verify alert warning message");
//            softAssert.assertTrue(lp.isExceedLoginAttemptsMessageDisplayed(
//                    "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour."),
//                    "Warning message should be displayed correctly");
//            softAssert.assertAll();
//            logger.info("TEST PASSED");
//        }catch (Exception e) {
//            logger.error("TEST FAILED: ", e);
//            Assert.fail("Test failed with exception: " + e.getMessage());
//        }
//        logger.info("***** Finished LG_04 - Đăng nhập thất bại khi nhập sai quá số lần cho phép ******");
//    }

}
