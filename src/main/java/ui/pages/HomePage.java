package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

import java.util.List;

public class HomePage {

    private WaitUtils waitUtils;

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(xpath = "//a[normalize-space()='Log in']")
    private WebElement loginLink;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    private WebElement registerLink;

    @FindBy(linkText = "Log out")
    private WebElement logoutLink;

    @FindBy(linkText = "My account")
    private WebElement myAccountLink;

    @FindBy(xpath = "//span[normalize-space()='Shopping cart']")
    private WebElement cartLink;

    @FindBy(xpath = "//span[text()='Wishlist']")
    private WebElement wishlistLink;

    @FindBy(css = "ul.list li.inactive a")
    private List<WebElement> categoryLinks;

    // ---- CONSTRUCTOR ----
    public HomePage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // ---- NAVIGATION METHODS ----
    public void clickLogin() {
        waitUtils.clickElement(loginLink);
    }

    public void clickRegister() {
        waitUtils.clickElement(registerLink);
    }

    public void clickLogout() {
        waitUtils.clickElement(logoutLink);
    }

    public void openMyAccount() {
        waitUtils.clickElement(myAccountLink);
    }

    public void openCart() {
        waitUtils.clickElement(cartLink);
    }

    public void openWishlist() {
        waitUtils.clickElement(wishlistLink);
    }

    // ---- VALIDATION METHODS ---// 
  
   
    public boolean isUserLoggedIn() {
        return waitUtils.waitForElementVisible(logoutLink).isDisplayed();
    }

    public boolean isUserLoggedOut() {
        return waitUtils.waitForElementVisible(loginLink).isDisplayed();
    }
}
