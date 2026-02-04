package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private WaitUtils waitUtils;    // ✅ instance of WaitUtils
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    // Locators using PageFactory
    @FindBy(id = "Email")
    private WebElement usernameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[contains(@class,'login-button')]")
    private WebElement loginButton;

    @FindBy(xpath = "//a[@class='account']")
    private WebElement accountEmail;
    
    @FindBy(xpath = "//a[text()='Log out']")
    private WebElement logoutLink;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver); // ✅ initialize WaitUtils
        PageFactory.initElements(driver, this); // ✅ initialize PageFactory
        logger.info("LoginPage initialized with driver: {}", driver);
    }

    // Actions
    public void enterUsername(String username) {
        logger.info("Entering username: {}", username);
        waitUtils.enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        waitUtils.enterText(passwordField, password);
    }

    public void clickLogin() {
        logger.info("Clicking Login button");
        waitUtils.clickElement(loginButton);
    }
    
    public void clickLogout() {
        logger.info("Clicking Logout link");
        waitUtils.clickElement(logoutLink);
    }

    // Combined Method (reusable)
    public void login(String user, String pass) {
        logger.info("Attempting login with user: {}", user);
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
        logger.info("Login submitted for user: {}", user);
    }

    // Assertion helper
    public String getLoggedInUserEmail() {
        String email = waitUtils.getElementText(accountEmail);
        logger.info("Logged in user email displayed: {}", email);
        return email;
    }
}