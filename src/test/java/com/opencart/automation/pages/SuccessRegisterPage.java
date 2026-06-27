package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SuccessRegisterPage extends BasePage{
    public SuccessRegisterPage(WebDriver driver) {
        super(driver);
    }
    private final By lblSuccessTitle = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
    private final By btnContinue = By.linkText("Continue");
    private final By breadcrumbSuccess = By.linkText("Success");

    public boolean isSuccessPageDisplayed() {
        return isDisplayed(breadcrumbSuccess);
    }

    public String getSuccessTitle() {
        return getText(lblSuccessTitle);
    }
    public void clickContinue() {
        click(btnContinue);
    }

    public void verifySuccessPage() {
        Assert.assertTrue(getSuccessTitle().contains("Your Account Has Been Created!"));
        Assert.assertTrue(isSuccessPageDisplayed(), "Success page should be displayed");

    }




}
