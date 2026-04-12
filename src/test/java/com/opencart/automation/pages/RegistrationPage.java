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

    //Error message

    @FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]") WebElement firtNameErrMsg;
    @FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]") WebElement lastNameErrMsg;
    @FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]") WebElement emailErrMsg;
    @FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]") WebElement telephoneErrMsg;
    @FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]") WebElement pwdErrMsg;
    @FindBy(xpath="//div[contains(text(),'Password confirmation does not match password!')]") WebElement confirmPwdMsg;
    @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") WebElement agreeTermsWarnMsg;


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

    //
//    public String getConfirmationMsg() {
//        try {
//            return getErrorMessage(firtNameErrMsg);
//        }catch(Exception e) {
//            return (e.getMessage());
//        }
//
//    }
    //
    //dùng DDT
    public void registerNewUser(String firstName,
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
        if (subscribeNewsletter.equalsIgnoreCase("yes")){
            setNewsletterYes();
        }else {
            setNewsletterNo();  //mặc định
        }
        if (agreeTerms.equalsIgnoreCase("yes")){
            setAgreePolicy();
        }

        clickContinue();

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
    //Method chung kiểm tra lỗi
    private boolean isErrorMessageDisplayed(WebElement errorElement, String expectedMsg) {
        try {
            String actualMsg = getErrorMessage(errorElement);
            return actualMsg.contains(expectedMsg) || actualMsg.equalsIgnoreCase(expectedMsg);
        }catch (Exception e) {
            return false;
        }
    }

    //Validation method
    public boolean isFirstNameErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(firtNameErrMsg, expectedMsg);
    }

    public boolean isLastNameErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(lastNameErrMsg, expectedMsg);
    }

    public boolean isEmailErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(emailErrMsg, expectedMsg);
    }
    public boolean isTelephoneErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(telephoneErrMsg, expectedMsg);
    }

    public boolean isPasswordErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(pwdErrMsg, expectedMsg);
    }

    public boolean isConfPwdErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(confirmPwdMsg, expectedMsg);
    }

    public boolean isPolicyErrorMsgDisplayed(String expectedMsg) {
        return isErrorMessageDisplayed(agreeTermsWarnMsg, expectedMsg);
    }

}
