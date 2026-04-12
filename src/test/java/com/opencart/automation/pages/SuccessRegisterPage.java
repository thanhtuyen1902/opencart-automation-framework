package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SuccessRegisterPage extends BasePage{
    public SuccessRegisterPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement lblSuccessTitle;
    @FindBy(xpath="//a[normalize-space()='Continue']") WebElement btnContinue;
    @FindBy(xpath="//a[normalize-space()='Success']") WebElement breadcrumbSuccess;


    public boolean isSuccessPageDisplayed() {
        return breadcrumbSuccess.isDisplayed();
    }

    public String getSuccessTitle() {
        waitForElementVisible(lblSuccessTitle);
        return lblSuccessTitle.getText().trim();
    }
    public void clickContinue() {
        click(btnContinue);
    }

    public void verifySuccessPage() {
        Assert.assertTrue(getSuccessTitle().contains("Your Account Has Been Created!"));
        Assert.assertTrue(isSuccessPageDisplayed(), "Success page should be displayed");

    }




}
