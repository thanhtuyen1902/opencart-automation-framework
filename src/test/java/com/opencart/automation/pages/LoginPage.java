package com.opencart.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath="//input[@id='input-email']")
    WebElement txtEmail;
    @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
    @FindBy(xpath="//div[@class='form-group']//a[normalize-space()='Forgotten Password']") WebElement lnkForgotPwd;
    @FindBy(xpath="//input[@value='Login']") WebElement btnLogin;

    public void setEmailAddress(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }
    public void setPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }
    public void clickLogin() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", btnLogin);
    }


}
