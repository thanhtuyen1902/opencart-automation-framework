package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    @FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading;
    @FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout;
    @FindBy(xpath="//a[normalize-space()='Continue']") WebElement btnContinueLogout;
    //Method
    public boolean isMyAccountPageExists() {
        try {
            return (msgHeading.isDisplayed());
        }catch(Exception e) {
            return false;
        }

    }
    public void clickLogout() {
        click(lnkLogout);
    }
    public void clickContinueLogout() {
        click(btnContinueLogout);
    }
}
