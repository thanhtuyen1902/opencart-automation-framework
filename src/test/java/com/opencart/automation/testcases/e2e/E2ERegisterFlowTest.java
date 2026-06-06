package com.opencart.automation.testcases.e2e;

import com.opencart.automation.core.BaseE2ETest;
import com.opencart.automation.models.User;
import com.opencart.automation.pages.*;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
@Test(groups = {"E2ETest"})
public class E2ERegisterFlowTest extends BaseE2ETest {
    @Test(description = "E2E_01")
    public void testE2ERegisterFlow() {
        String keyword = TestDataGenerator.getRandomBasicProducts();
        // STEP 1: Register
        logger.info("Step 1: Register");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickRegisterPage();
        RegistrationPage rp = new RegistrationPage(driver);
        User user = TestDataGenerator.generateUserWithDefaultPwd();
        SuccessRegisterPage success_rp = rp.register(user);
        logger.info("Verify register successfully");
        Assert.assertTrue(success_rp.isSuccessPageDisplayed(), "Success message should be displayed");
        success_rp.clickContinue();
        MyAccountPage acp = new MyAccountPage(driver);
        Assert.assertTrue(acp.isMyAccountPageExists(), "MyAccountPage should be displayed");
        //STEP 2: Search Product
        logger.info("Step 2: Search product");
        hp.search(keyword);
        SearchPage sp = new SearchPage(driver);
        logger.info("Verify search results are displayed");
        Assert.assertTrue(sp.isProductDisplayedInResults(keyword), "Product should be displayed in search results");
        //STEP 3: Add to cart
        logger.info("Step 3: Add product to cart from search results");
        sp.findProductByName(keyword).clickAddToCart();
        logger.info("Verify add to cart successfully");
        String expectedMsg = "Success: You have added " + keyword + " to your shopping cart!";
        Assert.assertTrue(sp.isSuccessAddToCartMessageDisplayed(expectedMsg),
                "Success message should be displayed with correct content");
        CartPage cp = new CartPage(driver);
        logger.info("Verify product in cart");
        hp.clickCartPage();
        logger.info("Navigate to Cart Page success");
        Assert.assertTrue(cp.isProductInCart(keyword), "Product should be displayed in cart");
        //STEP 4: Logout
        logger.info("Step 4: Logout");
        hp.clickMyAccount();
        hp.clickLogout();

    }
}
