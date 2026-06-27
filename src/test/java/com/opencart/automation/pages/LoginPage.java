package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    private final By txtEmail = By.xpath("//input[@id='input-email']");
    private final By txtPassword = By.xpath("//input[@id='input-password']");
    private final By lnkForgotPwd = By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
    private final By btnLogin = By.cssSelector("input.btn.btn-primary");
    //Error message
    private By warningMessage = By.cssSelector(".alert.alert-danger");

    //Method
    public void setEmailAddress(String email) {
        sendKeys(txtEmail, email);
    }
    public void setPassword(String password) {
        sendKeys(txtPassword, password);
    }
    public void clickLogin() {
        click(btnLogin);
    }

    public boolean isLoginFailedMessageDisplayed(String expectedMsg) {
        return isMessageDisplayed(warningMessage, expectedMsg);
    }

    //Thực hiện cả luồng login, sau login chuyển đến MyAccountPage
    public MyAccountPage login(String email, String password) {
        setEmailAddress(email);
        setPassword(password);
        clickLogin();
        return new MyAccountPage(driver);
    }


}
