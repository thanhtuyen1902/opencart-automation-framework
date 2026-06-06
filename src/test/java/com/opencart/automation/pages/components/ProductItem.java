package com.opencart.automation.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductItem {
    private WebDriver driver;
    private final WebElement productElement;
    private WebDriverWait wait;
    // Locators
    private final By productName = By.cssSelector(".caption h4 a");
    private final By btnAddToCart = By.cssSelector(".button-group button[onclick*='cart.add']");
    //Method
    public ProductItem(WebDriver driver, WebElement productElement) {
        this.driver = driver;
        this.productElement = productElement;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }
    // Get product name
    public String getProductName() {
        return productElement.findElement(productName).getText();
    }
    // Click Add to Cart button
    public void clickAddToCart() {
        WebElement btnAdd = wait.until(ExpectedConditions.elementToBeClickable(productElement.findElement(btnAddToCart)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", btnAdd);
    }

    // Click product to navigate to product details page
    public void clickProduct() {
//        productElement.findElement(productName).click();
        WebElement btnProduct = wait.until(ExpectedConditions.elementToBeClickable(productElement.findElement(productName)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", btnProduct);
    }

}
