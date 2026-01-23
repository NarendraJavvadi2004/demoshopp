package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

public class RegisterPage {
    private WaitUtils waitUtils;

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
    }

    // 🔹 Actions
    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            waitUtils.clickElement(maleRadio);
        } else {
            waitUtils.clickElement(femaleRadio);
        }
    }

    public void enterFirstName(String firstName) {
        waitUtils.enterText(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        waitUtils.enterText(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        waitUtils.enterText(emailField, email);
    }

    public void enterPassword(String password) {
        waitUtils.enterText(passwordField, password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        waitUtils.enterText(confirmPasswordField, confirmPassword);
    }

    public void clickRegister() {
        waitUtils.clickElement(registerButton);
    }

    // 🔹 Combined reusable method
    public void registerUser(String gender, String firstName, String lastName, String email, String password) {
        selectGender(gender);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(password);
        clickRegister();
    }

    // 🔹 Assertion helper
    public String getSuccessMessage() {
        waitUtils.waitForElementVisible(successMessage);   // ✅ instance call
        return waitUtils.getElementText(successMessage);   // ✅ instance call
    }


}