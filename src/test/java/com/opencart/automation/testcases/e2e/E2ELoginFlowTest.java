package com.opencart.automation.testcases.e2e;

import com.opencart.automation.base.BaseE2ETest;
import com.opencart.automation.pages.*;
import com.opencart.automation.utilities.ConfigReader;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2ELoginFlowTest extends BaseE2ETest{
    @Test(description = "E2E_02")
    public void testE2ELoginFlow() {
        String keyword = TestDataGenerator.getRandomBasicProducts();
        // STEP 1: Login
        logger.info("Step 1: Login");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clickLoginPage();
        LoginPage lp = new LoginPage(driver);
        MyAccountPage acp = lp.login(ConfigReader.getEmail(), ConfigReader.getPassword());
        logger.info("Verify login successfully");
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
        Assert.assertTrue(cp.isProductInCart(keyword), "Product should be displayed in cart");
        //STEP 4: Logout
        logger.info("Step 4: Logout");
        hp.clickMyAccount();
        hp.clickLogout();

    }
}
