package com.opencart.automation.pages;

import com.opencart.automation.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // locator elements
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
    @FindBy(css = "input.btn.btn-primary") WebElement btnContinue;

    //Error message
//    @FindBy(css = "#input-firstname + .text-danger") WebElement firstNameErrMsg;
//    @FindBy(css = "#input-lastname + .text-danger") WebElement lastNameErrMsg;
//    @FindBy(css = "#input-email + .text-danger") WebElement emailErrMsg;
//    @FindBy(css = "#input-telephone + .text-danger") WebElement telephoneErrMsg;
//    @FindBy(css = "#input-password + .text-danger") WebElement pwdErrMsg;
//    @FindBy(css = "#input-confirm + .text-danger") WebElement confirmPwdMsg;
//    @FindBy(css = ".alert.alert-danger") WebElement warningMessage;

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
//    public boolean isRegistrationPageStillDisplayed() {
//        return isElementDisplayed(txtFirstName);
//    }


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
