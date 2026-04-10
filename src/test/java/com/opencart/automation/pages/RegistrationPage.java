package com.opencart.automation.pages;

import com.opencart.automation.models.User;
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
    @FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
    @FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
    @FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
    @FindBy(xpath="//input[@value='0']") WebElement rdoNewsletterNo;
    @FindBy(xpath="//label[normalize-space()='Yes']") WebElement rdoNewsletterYes;
    @FindBy(xpath="//input[@name='agree']") WebElement chkAgreePolicy;
    @FindBy(xpath="//b[normalize-space()='Privacy Policy']") WebElement lnkPrivacyPolicy;
    @FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;




    public void setFirstName(String firstName){
        sendKeys(txtFirstName, firstName);
    }
    public void setLastName(String lastName){
        sendKeys(txtLastName, lastName);
    }
    public void setEmail(String email){
        sendKeys(txtEmail, email);
    }
    public void setPhoneNumber(String phone){
        sendKeys(txtTelephone, phone);
    }
    public void setPassword(String password){
        sendKeys(txtPassword, password);
    }
    public void setConfirmPassword(String confirmPwd){
        sendKeys(txtConfirmPassword, confirmPwd);
    }
    public void setNewsletterNo(){
        click(rdoNewsletterNo);
    }
    public void setNewsletterYes(){
        click(rdoNewsletterYes);
    }
    public void setAgreePolicy(){
        click(chkAgreePolicy);
    }
    public void clickLinkPolicy(){
        click(lnkPrivacyPolicy);
    }

    public void clickContinue(){
        click(btnContinue);
    }

//    public void registerNewUser(String firstName, String lastName, String email, String phone, String pwd, String confPwd) {
//        setFirstName(firstName);
//        setLastName(lastName);
//        setEmail(email);
//        setPhoneNumber(phone);
//        setPassword(pwd);
//        setConfirmPassword(confPwd);
////        setNewsletterNo();      //mặc định
//        setAgreePolicy();
//        clickContinue();
//
//    }
    public void fillRegisterForm(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPhoneNumber(user.getTelephone());
        setPassword(user.getPassword());
        setConfirmPassword(user.getConfirmPassword());
    //        setNewsletterNo();      //mặc định

    }
//    public void submitRegister() {
//        clickContinue();
//    }

    //Verification method



}
