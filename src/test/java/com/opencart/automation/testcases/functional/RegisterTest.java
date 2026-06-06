package com.opencart.automation.testcases.functional;

import com.opencart.automation.base.BaseTest;
import com.opencart.automation.models.User;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.MyAccountPage;
import com.opencart.automation.pages.RegistrationPage;
import com.opencart.automation.pages.SuccessRegisterPage;
import com.opencart.automation.utilities.DataProviders;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
/**
 * Lớp kiểm thử chức năng đăng ký tài khoản (Register).
 *
 * RG_01 – Đăng ký thành công với dữ liệu hợp lệ
 * RG_02 – Đăng ký với các trường để trống (DDT – Excel)
 * RG_03 – Đăng ký khi không tích chọn chính sách bảo mật
 * RG_04 – Đăng ký với email đã tồn tại trong hệ thống
 * RG_05 – Đăng ký với mật khẩu xác nhận không khớp
 */
@Test(groups = {"Register"})
public class RegisterTest extends BaseTest {
    private RegistrationPage rp;
    private SoftAssert softAssert;
    private MyAccountPage acp;
    @BeforeMethod
    public void navigateToRegisterPage() {
        logger.info("***** Starting RegisterTest *****");
        HomePage hp = new HomePage(driver);
        softAssert = new SoftAssert();
        logger.info("Click on My Account dropdown");
        hp.clickMyAccount();
        logger.info("Click on Register link to open RegisterPage");
        hp.clickRegisterPage();
        rp = new RegistrationPage(driver);
    }

    @Test(description = "RG_01 - Đăng ký tài khoản mới thành công")
    public void testRegisterSuccess() {
        logger.info("***** Starting RG_01 - Đăng ký tài khoản mới thành công ******");
        try {
            User user = TestDataGenerator.generateUserWithDefaultPwd();
            logger.info("Step 1: Fill registration form...");
            rp.fillRegisterForm(user);
            logger.info("Step 2: Agree to privacy policy");
            rp.setAgreePolicy();
            logger.info("Step 3: Click Continue");
            rp.clickContinue();
            SuccessRegisterPage success_rp = new SuccessRegisterPage(driver);
            logger.info("Step 4: Verify redirection to SuccessRegisterPage");
            softAssert.assertTrue(success_rp.getSuccessTitle().contains("Your Account Has Been Created!"),
                    "Success message should be displayed");
            softAssert.assertTrue(success_rp.isSuccessPageDisplayed(),
                    "SuccessPage should be displayed");
            logger.info("Step 5: Navigate to My Account page");
            success_rp.clickContinue();
            MyAccountPage acp = new MyAccountPage(driver);
            softAssert.assertTrue(acp.isMyAccountPageExists(),
                    "MyAccountPage should be displayed!");
            softAssert.assertAll();
            logger.info("Registered user: {}", user);
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished RG_01 - Đăng ký tài khoản mới thành công ******");
    }

    @Test(dataProvider = "negativeRegistrationData", dataProviderClass = DataProviders.class,
            description = "[Data Driven Test] Đăng ký thất bại khi để trống các trường bắt buộc")
    public void testRegisterEmptyFields(String testcaseId,
                                        String expectedErrorField,
                                        String firstName,
                                        String lastName,
                                        String email,
                                        String telephone,
                                        String password,
                                        String confirmPassword,
                                        String subscribeNewsletter,
                                        String agreeTerms,
                                        String expectedMessage) {

        logger.info("***** Starting RG_02 - Đăng ký thất bại khi để trống các trường bắt buộc ******");
        logger.info("***** [Data Driven Test] testcaseId={} - Missing {} ******", testcaseId, expectedErrorField);
        try {

            logger.info("Step 1: Fill registration form...");
            rp.registerDDT(firstName, lastName, email, telephone, password, confirmPassword, subscribeNewsletter, agreeTerms);
            logger.info("Step 2: Click on button Continue");
            rp.clickContinue();
            logger.info("Step 3: Validate expected message");
            String actualMsg = rp.getFieldError(expectedErrorField);
            logger.info("Empty field: "+ expectedErrorField);
            Assert.assertEquals(actualMsg, expectedMessage, "Error message should be displayed correctly");
            logger.info("Registering user: firstName={}, lastName={}",firstName, lastName);
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }

        logger.info("***** Finished RG_02 - Đăng ký thất bại khi để trống các trường bắt buộc ******");

    }

@Test(description = "RG_03 - Đăng ký thất bại khi chưa tích chọn Privacy Policy")
public void testRegisterNoPrivacyPolicy() {
    logger.info("***** Starting RG_03 - Đăng ký thất bại khi chưa tích chọn Privacy Policy ******");
    try {
        User user = TestDataGenerator.generateUserWithDefaultPwd();
        logger.info("Step 1: Fill registration form without accepting privacy policy");
        rp.fillRegisterForm(user);
        logger.info("Step 2: Click Continue");
        rp.clickContinue();
        logger.info("Step 3: Verify privacy policy error message");
        softAssert.assertTrue(rp.isPolicyErrorMsgDisplayed("Warning: You must agree to the Privacy Policy!"),
                "Privacy policy error message should be displayed");

        softAssert.assertAll();
        logger.info("Registered user: {}", user);
        logger.info("TEST PASSED");
    }catch (Exception e) {
        logger.error("TEST FAILED: ", e);
        Assert.fail("Test failed with exception: " + e.getMessage());
    }
    logger.info("***** Finished RG_03 - Đăng ký thất bại khi chưa tích chọn Privacy Policy ******");
}

    @Test(description = "RG_04 - Đăng ký thất bại khi Email đã được sử dụng")
    public void testRegisterDuplicateEmail() {
        logger.info("***** Starting RG_04 - Đăng ký thất bại khi Email đã được sử dụng ******");
        try {
            User user = TestDataGenerator.generateUserWithDuplicateEmail();
            logger.info("Step 1: Fill registration form with duplicate email");
            rp.fillRegisterForm(user);
            logger.info("Step 2: Agree to privacy policy");
            rp.setAgreePolicy();
            logger.info("Step 3: Click Continue");
            rp.clickContinue();
            logger.info("Step 4: Verify email error message");
            softAssert.assertTrue(rp.isEmailErrorDuplicateDisplayed("Warning: E-Mail Address is already registered!"),
                    "Email error message should be displayed");
            softAssert.assertAll();
            logger.info("Registered user: {}", user);
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished RG_04 - Đăng ký thất bại khi Email đã được sử dụng ******");
    }

    @Test(description = "RG_05 - Đăng ký thất bại khi nhập Password và Confirm Password không khớp")
    public void testRegisterPasswordMismatch() {
        logger.info("***** Starting RG_05 - Đăng ký thất bại khi nhập Password và Confirm Password không khớp ******");
        try {
            User user = TestDataGenerator.generateUserWithMismatchPwd();
            logger.info("Step 1: Fill registration form with duplicate email");
            rp.fillRegisterForm(user);
            logger.info("Step 2: Agree to privacy policy");
            rp.setAgreePolicy();
            logger.info("Step 3: Click Continue");
            rp.clickContinue();
            logger.info("Step 4: Verify email error message");
            softAssert.assertTrue(rp.isConfPwdErrorMsgDisplayed("Password confirmation does not match password!"),
                    "Confirm password error message should be displayed");
            softAssert.assertAll();
            logger.info("Registered user: {}", user);
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished RG_05 - Đăng ký thất bại khi nhập Password và Confirm Password không khớp ******");
    }
}
