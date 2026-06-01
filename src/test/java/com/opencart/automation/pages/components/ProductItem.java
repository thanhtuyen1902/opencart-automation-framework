package com.opencart.automation.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductItem {
    private WebDriver driver;
    private final WebElement productElement;

    // Locators
    private final By productName = By.cssSelector(".caption h4 a");
    private final By btnAddToCart = By.cssSelector(".button-group button[onclick*='cart.add']");
    //Method
    public ProductItem(WebDriver driver, WebElement productElement) {
        this.driver = driver;
        this.productElement = productElement;
    }
    // Get product name
    public String getProductName() {
        return productElement.findElement(productName).getText();
    }
    // Click Add to Cart button
    public void clickAddToCart() {
        productElement.findElement(btnAddToCart).click();
    }

    // Click product to navigate to product details page
    public void clickProduct() {
        productElement.findElement(productName).click();
    }

}
