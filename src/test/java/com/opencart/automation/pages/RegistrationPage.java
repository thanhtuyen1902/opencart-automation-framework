package com.opencart.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
    @FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
    @FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
    @FindBy(xpath="//input[@id='input-telephone']") WebElement txtPhoneNumber;
    @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
    @FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
    @FindBy(xpath="//input[@value='0']") WebElement rdoNewsletterNo;
    @FindBy(xpath="//label[normalize-space()='Yes']") WebElement rdoNewsletterYes;
    @FindBy(xpath="//input[@name='agree']") WebElement chkAgreePolicy;
    @FindBy(xpath="//b[normalize-space()='Privacy Policy']") WebElement lnkPrivacyPolicy;
    @FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;

    public void setFirstName(String firstName){
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
    }
    public void setLastName(String lastName){
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
    }
    public void setEmail(String email){
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }
    public void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }
    public void setConfirmPassword(String confirmPwd){
        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(confirmPwd);
    }
    public void setNewsletterNo(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", rdoNewsletterNo);
    }
    public void setNewsletterYes(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", rdoNewsletterYes);
    }
    public void setAggreePolicy(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", chkAgreePolicy);
    }
    public void clickLinkPolicy(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", lnkPrivacyPolicy);
    }

    public void clickContinue(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", btnContinue);
    }



}
