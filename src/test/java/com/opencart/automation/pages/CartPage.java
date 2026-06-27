package com.opencart.automation.pages;

import com.opencart.automation.pages.components.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    private final By cartItems = By.cssSelector("table.table-bordered tbody tr");

    //Method
    public List<CartItem> getCartItems() {
        return driver.findElements(cartItems)
                .stream()
                .filter(row -> !row.findElements(By.cssSelector("td.text-left a")).isEmpty()) // Lọc bỏ các hàng không phải sản phẩm
                .map(CartItem::new)
                .collect(Collectors.toList());
    }

    public boolean isProductInCart(String productName) {
        return getCartItems()
                .stream()
                .anyMatch(item -> item.getProductName().equalsIgnoreCase(productName));
    }

    public CartItem getCartItemByProductName(String productName) {
        wait.until(driver -> isProductInCart(productName));
        return getCartItems()
                .stream()
                .filter(item -> item.getProductName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart: " + productName));
    }

    public void removeAllProductsFromCart() {
        By removeBtn = By.cssSelector("button.btn-danger");
        while (!driver.findElements(removeBtn).isEmpty()) {
            driver.findElements(removeBtn)
                    .get(0)
                    .click();
        }
    }




}
