package com.opencart.automation.testcases.functional;

import com.opencart.automation.core.BaseTest;
import com.opencart.automation.pages.*;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Test(groups = {"AddToCart"})
public class AddToCartTest extends BaseTest {
    private SoftAssert softAssert;
    private HomePage hp;
    private CartPage cp;
    private ProductPage pp;

    @Test(description = "AC_01 - Thêm sản phẩm mới vào giỏ hàng thành công")
    public void testAddToCartFromHomePage() {
        logger.info("***** Starting AC_01 - Thêm sản phẩm mới vào giỏ hàng thành công ******");
        try {
            String productName = TestDataGenerator.getRandomBasicProducts();
            hp = new HomePage(driver);
            logger.info("Step 1: Add product {} to cart from HomePage", productName);
            hp.findProductByName(productName).clickAddToCart();
            logger.info("Step 2: Verify message success displayed");
            softAssert = new SoftAssert();
            String expectedMsg = "Success: You have added " + productName + " to your shopping cart!";
            softAssert.assertTrue(hp.isSuccessAddToCartMessageDisplayed(expectedMsg),
                    "Success message should be displayed with correct content");
            logger.info("Step 3: Verify product {} is added to cart successfully", productName);
            logger.info("Step 3.1: Navigate to CartPage");
            hp.clickCartPage();
            cp = new CartPage(driver);
            logger.info("Step 3.2: Verify product {} is displayed in cart", productName);
            softAssert.assertTrue(cp.isProductInCart(productName),
                    "Product should be displayed in cart");
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished AC_01 - Thêm sản phẩm mới vào giỏ hàng thành công ******");

    }

    @Test(description = "AC_02 - Kiểm tra logic cộng dồn khi thêm trùng sản phẩm")
    public void testAddToCartDuplicate() {
        logger.info("***** Starting AC_02 - Kiểm tra logic cộng dồn khi thêm trùng sản phẩm ******");
        try {
            String productName = TestDataGenerator.getRandomBasicProducts();
            hp = new HomePage(driver);
            logger.info("Step 1: Add product {} to cart from HomePage", productName);
            softAssert = new SoftAssert();
            hp.findProductByName(productName).clickAddToCart();
            logger.info("Step 2: Verify message success displayed");
            String expectedMsg = "Success: You have added " + productName + " to your shopping cart!";
            softAssert.assertTrue(hp.isSuccessAddToCartMessageDisplayed(expectedMsg),
                    "Success message should be displayed with correct content");
            logger.info("Step 1.1: Click the first time: done");
            hp.findProductByName(productName).clickAddToCart();
            logger.info("Step 2: Verify message success displayed");
            softAssert.assertTrue(hp.isSuccessAddToCartMessageDisplayed(expectedMsg),
                    "Success message should be displayed with correct content");
            logger.info("Step 1.1: Click the second time: done");

            logger.info("Step 3: Verify product {} is added to cart successfully", productName);
            Thread.sleep(100);
            logger.info("Step 3.1: Navigate to CartPage");
            hp.clickCartPage();
            cp = new CartPage(driver);
            logger.info("Step 3.2: Verify product {} is displayed in cart", productName);
            softAssert.assertTrue(cp.isProductInCart(productName),
                    "Product should be displayed in cart");
            logger.info("Step 3.3: Verify quantity of product {} is 2 in cart", productName);
            softAssert.assertEquals(cp.getCartItemByProductName(productName).getQuantity(), 2,
                    "Quantity of product should be 2 when added to cart twice");
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished AC_02 - Kiểm tra logic cộng dồn khi thêm trùng sản phẩm ******");
    }

    @Test(description = "AC_03 - Thêm sản phẩm thành công từ trang chi tiết")
    public void testAddToCartFromProductPage() {
        logger.info("***** Starting AC_03 - Thêm sản phẩm thành công từ trang chi tiết ******");
        try {
            String productName = TestDataGenerator.getRandomBasicProducts();
            int quantity = 2;
            hp = new HomePage(driver);
            logger.info("Step 1: Navigate to ProductPage of {}", productName);
            hp.findProductByName(productName).clickProduct();
            logger.info("Verify product name in breadcrumb {}", productName);
            softAssert = new SoftAssert();
            pp = new ProductPage(driver);
            softAssert.assertEquals(pp.getBreadcrumbProductName(), productName,
                    "Product name should be displayed in breadcrumb");
            logger.info("Step 2: Add product {} to cart", productName);
            logger.info("Step 2.1: Enter quantity = {}", quantity);
            pp.setQuantity(quantity);
            logger.info("Step 2.2: Click Add to Cart");
            pp.clickAddToCart();
            logger.info("Step 3: Verify message success displayed");
            String expectedMsg = "Success: You have added " + productName + " to your shopping cart!";
            softAssert.assertTrue(hp.isSuccessAddToCartMessageDisplayed(expectedMsg),
                    "Success message should be displayed with correct content");
            logger.info("Step 4: Verify product {} is added to cart successfully", productName);
            logger.info("Step 4.1: Navigate to CartPage");
            hp.clickCartPage();
            cp = new CartPage(driver);
            logger.info("Step 4.2: Verify product {} is displayed in cart", productName);
            softAssert.assertTrue(cp.isProductInCart(productName),
                    "Product should be displayed in cart");
            logger.info("Step 4.3: Verify quantity of product {} is {} in cart", productName, quantity);
            softAssert.assertEquals(cp.getCartItemByProductName(productName).getQuantity(), 2,
                    "Quantity of product should be " + quantity);
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished AC_03 - Thêm sản phẩm thành công từ trang chi tiết ******");

    }
}
