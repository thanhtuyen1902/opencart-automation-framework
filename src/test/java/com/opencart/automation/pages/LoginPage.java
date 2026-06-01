package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    @FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
    @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
    @FindBy(xpath="//div[@class='form-group']//a[normalize-space()='Forgotten Password']") WebElement lnkForgotPwd;
//    @FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
    private By btnLogin = By.cssSelector("input.btn.btn-primary");
    //Error message
    @FindBy(css = ".alert.alert-danger") WebElement warningMessage;

    //Method
    public void setEmailAddress(String email) {
        sendKeys(txtEmail, email);
    }
    public void setPassword(String password) {
        sendKeys(txtPassword, password);
    }
    public void clickLogin() {
//        click(btnLogin);
        click(driver.findElement(btnLogin));
    }

    public boolean isLoginFailedMessageDisplayed(String expectedMsg) {
        return isMessageDisplayed(warningMessage, expectedMsg);
    }
    public boolean isExceedLoginAttemptsMessageDisplayed(String expectedMsg) {
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
