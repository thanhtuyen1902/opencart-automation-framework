package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    //Locators
    private final By msgHeading = By.xpath("//h2[normalize-space()='My Account']");
    private final By lnkLogout = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
    private final By btnContinueLogout = By.xpath("//a[normalize-space()='Continue']");
    //Method
    public boolean isMyAccountPageExists() {
        try {
            return isDisplayed(msgHeading);
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
