package com.opencart.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//p/án: Sau này có thể tách thành HeaderComponent
public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(xpath="//span[@class='caret']") WebElement ddlMyAccount;
    //====My Account Dropdown
    @FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegisterPage;
    @FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLoginPage;
    @FindBy(xpath="//a[normalize-space()='Logout']") WebElement lnkLogout;
    @FindBy(css="a[title='Shopping Cart']") WebElement lnkCartPage;
    //Search elements
    @FindBy(xpath="//input[@placeholder='Search']") WebElement txtSearch;
    @FindBy(xpath="//button[@class='btn btn-default btn-lg']") WebElement btnSearch;

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
