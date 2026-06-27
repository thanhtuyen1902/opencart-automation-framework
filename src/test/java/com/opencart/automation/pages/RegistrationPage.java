package com.opencart.automation.pages;

import com.opencart.automation.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // locator elements
    private final By txtFirstName = By.id("input-firstname");
    private final By txtLastName = By.id("input-lastname");
    private final By txtEmail = By.id("input-email");
    private final By txtTelephone = By.id("input-telephone");
    private final By txtPassword = By.id("input-password");
    private final By txtConfirmPassword = By.id("input-confirm");
    private final By rdoNewsletterNo = By.cssSelector("input[name='newsletter'][value='0']");
    private final By rdoNewsletterYes = By.cssSelector("input[name='newsletter'][value='1']");
    private final By chkAgreePolicy = By.name("agree");
    private final By lnkPrivacyPolicy = By.linkText("Privacy Policy");
    private final By btnContinue = By.cssSelector("input.btn.btn-primary");
    //Error message
    private final By firstNameErrMsg = By.cssSelector("#input-firstname + .text-danger");
    private final By lastNameErrMsg = By.cssSelector("#input-lastname + .text-danger");
    private final By emailErrMsg = By.cssSelector("#input-email + .text-danger");
    private final By telephoneErrMsg = By.cssSelector("#input-telephone + .text-danger");
    private final By pwdErrMsg = By.cssSelector("#input-password + .text-danger");
    private final By confirmPwdMsg = By.cssSelector("#input-confirm + .text-danger");
    private final By warningMessage = By.cssSelector(".alert.alert-danger");


    //Action methods
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
    public void setNewsletter(String subscribeNewsletter) {
        if(subscribeNewsletter.equalsIgnoreCase("yes")){
            setNewsletterYes();
        }else {
            setNewsletterNo();  //mặc định
        }
    }

    public void clickContinue(){
        click(btnContinue);
    }


    //dùng DDT
    public void registerDDT(String firstName,
                            String lastName,
                            String email,
                            String telephone,
                            String pwd,
                            String confPwd,
                            String subscribeNewsletter,
                            String agreeTerms) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(telephone);
        setPassword(pwd);
        setConfirmPassword(confPwd);
        setNewsletter(subscribeNewsletter);
        if (agreeTerms.equalsIgnoreCase("true")){
            setAgreePolicy();
        }

        clickContinue();

    }
    public SuccessRegisterPage register(User user) {
        fillRegisterForm(user);
        setNewsletterYes();
        setAgreePolicy();
        clickContinue();
        return new SuccessRegisterPage(driver);
    }
    //dùng fake data
    public void fillRegisterForm(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setEmail(user.getEmail());
        setPhoneNumber(user.getTelephone());
        setPassword(user.getPassword());
        setConfirmPassword(user.getConfirmPassword());
    //        setNewsletterNo();      //mặc định

    }

    //Verification method


    //Validation method
    public boolean isEmailErrorDuplicateDisplayed(String expectedMsg) {
        return isMessageDisplayed(warningMessage, expectedMsg);
    }

    public boolean isConfPwdErrorMsgDisplayed(String expectedMsg) {
        return isMessageDisplayed(confirmPwdMsg, expectedMsg);
    }

    public boolean isPolicyErrorMsgDisplayed(String expectedMsg) {
        return isMessageDisplayed(warningMessage, expectedMsg);
    }
    //DDT
    public String getFieldError(String fieldName) {
        switch(fieldName.toLowerCase()) {
            case "firstname":
                return getErrorMessage2(firstNameErrMsg);
            case "lastname":
                return getErrorMessage2(lastNameErrMsg);
            case "email":
                return getErrorMessage2(emailErrMsg);
            case "telephone":
                return getErrorMessage2(telephoneErrMsg);
            case "password":
                return getErrorMessage2(pwdErrMsg);
            default:
                throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }

    }

}
