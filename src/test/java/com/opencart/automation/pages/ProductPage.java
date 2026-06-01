package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    @FindBy(xpath="//button[@id='button-cart']") WebElement addToCartBtn;
    @FindBy(xpath="//input[@id='input-quantity']") WebElement txtQuantity;
    @FindBy(css = ".breadcrumb li:last-child a") WebElement breadcrumbProductName;
    //Method
    public void clickAddToCart() {
        click(addToCartBtn);
    }
    public String getBreadcrumbProductName() {
        return breadcrumbProductName.getText();
    }
    public void setQuantity(int quantity) {
        sendKeys(txtQuantity, String.valueOf(quantity));
    }

}
