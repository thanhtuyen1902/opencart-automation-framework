package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    private final By addToCartBtn = By.id("button-cart");
    private final By txtQuantity = By.id("input-quantity");
    private final By breadcrumbProductName = By.cssSelector(".breadcrumb li:last-child a");
    //Method
    public void clickAddToCart() {
        click(addToCartBtn);
    }
    public String getBreadcrumbProductName() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(breadcrumbProductName))
                .getText()
                .trim();
    }
    public void setQuantity(int quantity) {
        sendKeys(txtQuantity, String.valueOf(quantity));
    }

}
