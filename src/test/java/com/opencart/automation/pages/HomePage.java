package com.opencart.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//p/án: Sau này có thể tách thành HeaderComponent
public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    //Locators
    private final By ddlMyAccount = By.xpath("//span[@class='caret']");
    // ==== My Account Dropdown
    private final By lnkRegisterPage = By.xpath("//a[normalize-space()='Register']");
    private final By lnkLoginPage = By.xpath("//a[normalize-space()='Login']");
    private final By lnkLogout = By.xpath("//a[normalize-space()='Logout']");
    private final By lnkCartPage = By.cssSelector("a[title='Shopping Cart']");
    // Search elements
    private final By txtSearch = By.xpath("//input[@placeholder='Search']");
    private final By btnSearch = By.xpath("//button[@class='btn btn-default btn-lg']");

    //Method
    public void clickMyAccount() {
        click(ddlMyAccount);
    }
    //My account dropdown
    public void clickRegisterPage() {
        click(lnkRegisterPage);
    }
    public void clickLoginPage() {
        click(lnkLoginPage);
    }
    public void clickLogout() {
        click(lnkLogout);
    }

    public void clickSearchButton() {
        click(btnSearch);
    }
    public void clickCartPage() {
        click(lnkCartPage);
    }
    public void setSearchKeyword(String keyword) {
        sendKeys(txtSearch, keyword);
    }
    public void openHomePage(String url) {
        driver.get(url);
    }

    //Thực hiện hoàn tất hành động search
    public void search(String keyword) {
        sendKeys(txtSearch, keyword);
        click(btnSearch);

    }


}
