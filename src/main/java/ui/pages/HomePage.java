package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePage {

    private WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(HomePage.class);

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
    
    @FindBy(css = "div[id='nivo-slider']")
    private WebElement homepagePhotho;

    // ---- CONSTRUCTOR ----
    public HomePage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        logger.info("HomePage initialized with driver: {}", driver);
    }

    // ---- NAVIGATION METHODS ----
    public void clickLogin() {
        logger.info("Clicking on Login link");
        waitUtils.clickElement(loginLink);
    }

    public void clickRegister() {
        logger.info("Clicking on Register link");
        waitUtils.clickElement(registerLink);
    }

    public void clickLogout() {
        logger.info("Clicking on Logout link");
        waitUtils.clickElement(logoutLink);
    }

    public void openMyAccount() {
        logger.info("Opening My Account page");
        waitUtils.clickElement(myAccountLink);
    }

    public void openCart() {
        logger.info("Opening Shopping Cart");
        waitUtils.clickElement(cartLink);
    }

    public void openWishlist() {
        logger.info("Opening Wishlist");
        waitUtils.clickElement(wishlistLink);
    }

    // ---- VALIDATION METHODS ----
    public boolean isUserLoggedIn() {
        boolean loggedIn = waitUtils.waitForElementVisible(logoutLink).isDisplayed();
        logger.info("User logged in status: {}", loggedIn);
        return loggedIn;
    }

    public boolean isUserLoggedOut() {
        boolean loggedOut = waitUtils.waitForElementVisible(loginLink).isDisplayed();
        logger.info("User logged out status: {}", loggedOut);
        return loggedOut;
    }
    public boolean isHomepagephothoVisible() {
    	return waitUtils.waitForElementVisible(homepagePhotho).isDisplayed();
    }
}