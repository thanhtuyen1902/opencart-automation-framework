package com.opencart.automation.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartItem {
    private final WebElement cartItemElement;
//    private final By productName = By.cssSelector("td:nth-child(2) a");
    private final By productName = By.cssSelector("td.text-left a");
    private final By quantityInput = By.cssSelector("td:nth-child(4) input");
    private final By totalPrice = By.cssSelector("td:nth-child(6)");

    public CartItem(WebElement cartItemElement) {
        this.cartItemElement = cartItemElement;
        //debug
//        System.out.println(
//                cartItemElement.getAttribute("outerHTML")
//        );
    }
    // Get product name
    public String getProductName() {
        return cartItemElement.findElement(productName).getText();
    }
    // Get quantity
    public int getQuantity() {
        String value = cartItemElement.findElement(quantityInput).getAttribute("value");
        return Integer.parseInt(value);
    }
    // Get total price
    public String getTotalPrice() {
        return cartItemElement.findElement(totalPrice).getText();
    }
}
