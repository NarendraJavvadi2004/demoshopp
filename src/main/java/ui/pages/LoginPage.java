package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

public class LoginPage {

    private WaitUtils waitUtils;    // ✅ instance of WaitUtils

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
    }

    // Actions
    public void enterUsername(String username) {
        waitUtils.enterText(usernameField, username);
    }

    public void enterPassword(String password) {
        waitUtils.enterText(passwordField, password);
    }

    public void clickLogin() {
        waitUtils.clickElement(loginButton);
    }
    
    public void clickLogout() {
        waitUtils.clickElement(logoutLink);
    }
    // Combined Method (reusable)
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    // Assertion helper
    public String getLoggedInUserEmail() {
        return waitUtils.getElementText(accountEmail);
    }

	
}