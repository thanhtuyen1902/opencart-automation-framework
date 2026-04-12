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
        sendKeys(txtEmail, email);
    }
    public void setPassword(String password) {
        sendKeys(txtPassword, password);
    }
    public void clickLogin() {
        click(btnLogin);
    }

    //Thực hiện cả luồng login
    public void login(String email, String password) {
        setEmailAddress(email);
        setPassword(password);
        clickLogin();
    }


}
