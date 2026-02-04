package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterPage {
    private WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(RegisterPage.class);

    // 🔹 Locators using PageFactory
    @FindBy(id = "gender-male")
    private WebElement maleRadio;

    @FindBy(id = "gender-female")
    private WebElement femaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameField;

    @FindBy(id = "LastName")
    private WebElement lastNameField;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(xpath = "//div[normalize-space()='Your registration completed']")
    private WebElement successMessage;

    // 🔹 Constructor
    public RegisterPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        logger.info("RegisterPage initialized with driver: {}", driver);
    }

    // 🔹 Actions
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            logger.info("Selecting gender: Male");
            waitUtils.clickElement(maleRadio);
        } else {
            logger.info("Selecting gender: Female");
            waitUtils.clickElement(femaleRadio);
        }
    }

    public void enterFirstName(String firstName) {
        logger.info("Entering first name: {}", firstName);
        waitUtils.enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        logger.info("Entering last name: {}", lastName);
        waitUtils.enterText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        logger.info("Entering email: {}", email);
        waitUtils.enterText(emailField, email);
    }

    public void enterPassword(String password) {
        logger.info("Entering password");
        waitUtils.enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        logger.info("Entering confirm password");
        waitUtils.enterText(confirmPasswordField, confirmPassword);
    }

    public void clickRegister() {
        logger.info("Clicking Register button");
        waitUtils.clickElement(registerButton);
    }

    // 🔹 Combined reusable method
    public void registerUser(String gender, String firstName, String lastName, String email, String password) {
        logger.info("Starting user registration with email: {}", email);
        selectGender(gender);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
        logger.info("Registration form submitted for user: {}", email);
    }

    // 🔹 Assertion helper
    public String getSuccessMessage() {
        waitUtils.waitForElementVisible(successMessage);
        String message = waitUtils.getElementText(successMessage);
        logger.info("Registration success message displayed: {}", message);
        return message;
    }
}