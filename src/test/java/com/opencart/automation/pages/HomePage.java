package com.opencart.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//span[@class='caret']") WebElement ddlMyAccount;
    @FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegisterPage;
    @FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLoginPage;
    @FindBy(xpath="//input[@placeholder='Search']") WebElement txtSearch;
    @FindBy(xpath="//button[@class='btn btn-default btn-lg']") WebElement btnSearch;

    public void clickMyAccount() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", ddlMyAccount);
    }
    public void clickRegisterPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", lnkRegisterPage);
    }
    public void clickLoginPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", lnkLoginPage);
    }
}
