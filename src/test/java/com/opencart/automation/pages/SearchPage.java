package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    private final By headerSearchResults = By.cssSelector("div#content h1");
    private final By inputSearch = By.cssSelector("#input-search");
    private final By searchBar = By.cssSelector("div#product-search li:nth-child(1)");
    private final By productTitles = By.cssSelector(".product-thumb .caption h4 a");
    // Case no results found
    private final By noResultsMsg = By.xpath("//p[contains(text(),'There is no product that matches the search criteria.')]");
    //Method
    public String getHeaderSearchResults() {
        return getText(headerSearchResults);
    }
    public String getInputSearchKeyword() {
        return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(inputSearch))
            .getAttribute("value");
    }
    public boolean isSearchBarDisplayed() {
        return isDisplayed(searchBar);
    }
    public boolean isSearchHeaderDisplayedCorrectly(String keyword) {
        return getText(headerSearchResults).equals("Search - " + keyword);
    }
    // case search keyword matches exactly product title in search results
    public boolean isProductDisplayedExactInResults(String keyword) {
        List<WebElement> products = driver.findElements(productTitles);
        for (WebElement title : products) {
            if (title.getText().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
    // case-insensitive
    public boolean isProductDisplayedInResults(String keyword) {
        List<WebElement> products = driver.findElements(productTitles);
        for (WebElement title : products) {
            if (title.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    // Case no results found
    public boolean isNoResultsMessageDisplayed(String expectedMsg) {
        return isMessageDisplayed(noResultsMsg, expectedMsg);
    }









}
