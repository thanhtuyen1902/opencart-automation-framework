package com.opencart.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //tự động khởi tạo WebElement được đánh dấu @FindBy trong class hiện tại
        PageFactory.initElements(driver, this);
        //Khởi tạo Wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }
    //Các method chung có thể dùng lại ở mọi page
    protected void click(WebElement element) {
        waitForElementClickable(element);
        try {
            element.click();
        } catch(Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click()", element);
        }

    }

    protected void sendKeys(WebElement element, String text) {
        waitForElementVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    public String getErrorMessage(WebElement element) {
        try {
            waitForElementVisible(element);
            return element.getText().trim();
        } catch (Exception e) {
            return ""; // Không tìm thấy thông báo lỗi
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

}
