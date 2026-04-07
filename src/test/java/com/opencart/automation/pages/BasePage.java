package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
        //tự động khởi tạo WebElement được đánh dấu @FindBy trong class hiện tại
        PageFactory.initElements(driver, this);
    }
}
