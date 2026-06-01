package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    @FindBy(css="div[id='content'] h1") WebElement headerSearchResults;
    @FindBy(css="#input-search") WebElement inputSearch;
    @FindBy(css="div[id='product-search'] li:nth-child(1)") WebElement searchBar;
    @FindBy(css = ".product-thumb .caption h4 a") List<WebElement> productTitles;
    // Case no results found
    @FindBy(xpath="//p[contains(text(),'There is no product that matches the search criteria.')]") WebElement noResultsMsg;

    //Method
    public String getHeaderSearchResults() {
        return headerSearchResults.getText();
    }
    public String getInputSearchKeyword(String keyword) {
        return inputSearch.getAttribute("value");
    }
    public boolean isSearchBarDisplayed() {
        return searchBar.isDisplayed();
    }
    public boolean isSearchHeaderDisplayedCorrectly(String keyword) {
        return headerSearchResults.getText().equals("Search - " + keyword);
    }
    // case search keyword matches exactly product title in search results
    public boolean isProductDisplayedExactInResults(String keyword) {
        for (WebElement title : productTitles) {
            if (title.getText().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
    // case-insensitive
    public boolean isProductDisplayedInResults(String keyword) {
        for (WebElement title : productTitles) {
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
