package com.opencart.automation.testcases.functional;

import com.opencart.automation.core.BaseTest;
import com.opencart.automation.pages.HomePage;
import com.opencart.automation.pages.SearchPage;
import com.opencart.automation.utilities.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
@Test(groups = {"Search"})
public class SearchTest extends BaseTest {
    private SoftAssert softAssert;
    private SearchPage sp;
    @Test(description = "SP_01 - Tìm kiếm thành công với từ khóa chính xác")
    public void testSearchExactKeyword() {
        logger.info("***** Starting SP_01 - Tìm kiếm thành công với từ khóa chính xác ******");
        try {
            String keyword = TestDataGenerator.getRandomExactKeyword();
            HomePage hp = new HomePage(driver);
            logger.info("Step 1: Enter search keyword");
            hp.setSearchKeyword(keyword);
            logger.info("Step 2: Click search button");
            hp.clickSearchButton();
            logger.info("Step 3: Validate search results");
            softAssert = new SoftAssert();
            sp = new SearchPage(driver);
            logger.info("Validate search bar is displayed");
            softAssert.assertTrue(sp.isSearchBarDisplayed(), "Search bar should be displayed on SearchPage");
            logger.info("Validate format search header is displayed correctly");
            softAssert.assertTrue(sp.isSearchHeaderDisplayedCorrectly(keyword),
                    "Search header should display with format search - keyword");
            logger.info("Header value: {}", sp.getHeaderSearchResults());
            logger.info("Validate search results for keyword: {}", keyword);
            softAssert.assertTrue(sp.isProductDisplayedExactInResults(keyword),
                    "Product with exact keyword should be displayed in search results");
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished SP_01 - Tìm kiếm thành công với từ khóa chính xác ******");

    }

    @Test(description = "SP_02 - Tìm kiếm trường hợp không có sản phẩm khớp")
    public void testSearchNoMatchingProduct() {
        logger.info("***** Starting SP_02 - Tìm kiếm trường hợp không có sản phẩm khớp ******");
        try {
            String keyword = TestDataGenerator.getRandomInvalidKeyword();
            HomePage hp = new HomePage(driver);
            logger.info("Step 1: Enter search keyword");
            hp.setSearchKeyword(keyword);
            logger.info("Step 2: Click search button");
            hp.clickSearchButton();
            logger.info("Step 3: Validate search results");
            softAssert = new SoftAssert();
            sp = new SearchPage(driver);
            logger.info("Validate search bar is displayed");
            softAssert.assertTrue(sp.isSearchBarDisplayed(), "Search bar should be displayed on SearchPage");
            logger.info("Validate format search header is displayed correctly");
            softAssert.assertTrue(sp.isSearchHeaderDisplayedCorrectly(keyword),
                    "Search header should display with format search - keyword");
            logger.info("Header value: {}", sp.getHeaderSearchResults());
            logger.info("Validate search results for keyword: {}", keyword);
            softAssert.assertTrue(sp.isNoResultsMessageDisplayed("There is no product that matches the search criteria."),
                    "No results message should be displayed when no products match the search keyword.");
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished SP_02 - Tìm kiếm trường hợp không có sản phẩm khớp ******");

    }

    @Test(description = "SP_03 - Tìm kiếm không phân biệt hoa thường")
    public void testSearchCaseInsensitive() {
        logger.info("***** Starting SP_03 - Tìm kiếm không phân biệt hoa thường ******");
        try {
            String keyword = TestDataGenerator.getRandomInsensitiveKeyword();
            HomePage hp = new HomePage(driver);
            logger.info("Step 1: Enter search keyword");
            hp.setSearchKeyword(keyword);
            logger.info("Step 2: Click search button");
            hp.clickSearchButton();
            logger.info("Step 3: Validate search results");
            softAssert = new SoftAssert();
            sp = new SearchPage(driver);
            logger.info("Validate search bar is displayed");
            softAssert.assertTrue(sp.isSearchBarDisplayed(), "Search bar should be displayed on SearchPage");
            logger.info("Validate format search header is displayed correctly");
            softAssert.assertTrue(sp.isSearchHeaderDisplayedCorrectly(keyword),
                    "Search header should display with format search - keyword");
            logger.info("Header value: {}", sp.getHeaderSearchResults());
            logger.info("Validate search results for keyword: {}", keyword);
            softAssert.assertTrue(sp.isProductDisplayedInResults(keyword),
                    "Product with keyword should be displayed in search results regardless of case");
            softAssert.assertAll();
            logger.info("TEST PASSED");
        }catch (Exception e) {
            logger.error("TEST FAILED: ", e);
            Assert.fail("Test failed with exception: " + e.getMessage());
        }
        logger.info("***** Finished SP_03 - Tìm kiếm không phân biệt hoa thường ******");

    }
}
