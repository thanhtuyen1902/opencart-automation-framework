package com.opencart.automation.pages;

import com.opencart.automation.pages.components.ProductItem;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    // msg success add to cart
    @FindBy(xpath="//div[@class='alert alert-success alert-dismissible']") WebElement msgAddToCartSuccess;
    // Product items in search results
    private final By productThumbs = By.cssSelector(".product-thumb");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //tự động khởi tạo WebElement được đánh dấu @FindBy trong class hiện tại
//        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        //Khởi tạo Wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.wait.ignoring(StaleElementReferenceException.class);

    }
    //Các method chung có thể dùng lại ở mọi page
    protected void click(WebElement element) {
        waitForElementClickable(element);
        try {
            element.click();
        } catch(ElementNotInteractableException e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
        }

    }

    protected void sendKeys(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    protected String getErrorMessage(WebElement element) {
        try {
            waitForElementVisible(element);
            return element.getText().trim();
        } catch (Exception e) {
            System.err.println("Error when getting error message: " + e.getMessage());
            return "";
        }
    }

    protected String getErrorMessage2(By locator) {
//        WebElement element = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(locator));
//
//        return element.getText().trim();
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText().trim();
        } catch (UnhandledAlertException e) {
            System.out.println("Unexpected alert dismissed, text was: " + e.getAlertText());
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText().trim();
        }
    }

    protected boolean isMessageDisplayed(WebElement element, String expectedMsg) {
        try {
            String actualMsg = getErrorMessage(element);
            return actualMsg.contains(expectedMsg) || actualMsg.equalsIgnoreCase(expectedMsg);
        }catch (Exception e) {
            return false;
        }
    }

    //Các method chung sử dụng wait
    protected void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected void waitForElementClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitForTextPresent(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean isSuccessAddToCartMessageDisplayed(String expectedMsg) {
        return isMessageDisplayed(msgAddToCartSuccess, expectedMsg);
    }

    // Work with product items in search results

    /**
     * Lấy tất cả products trên page
     * biến toàn bộ HTML product thành 1 list WebElement để dễ thao tác
     */

    public List<ProductItem> getProductItems() {
        return driver.findElements(productThumbs)
                .stream()
                .map(element -> new ProductItem(driver, element))
                .collect(Collectors.toList());
    }

    // Tìm đúng product object theo tên
    public ProductItem findProductByName(String productName) {
        return getProductItems()
                .stream()
                .filter(item -> item.getProductName().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Không tìm thấy product nào có tên: " + productName));
    }
}
