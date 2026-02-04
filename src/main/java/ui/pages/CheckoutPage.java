package ui.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutPage {

    // ✅ immutable once set
    private final WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(CheckoutPage.class);

    // ------------------- Step 1: Billing Address -------------------
    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement billingFirstName;

    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement billingLastName;

    @FindBy(id = "BillingNewAddress_Email")
    private WebElement billingEmail;

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement billingCountry;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement billingCity;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement billingAddress1;

    @FindBy(id = "BillingNewAddress_Address2")
    private WebElement billingAddress2;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement billingZip;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement billingPhone;

    @FindBy(id = "billing-address-select")
    private WebElement addressDropdown;

    @FindBy(xpath = "//input[@onclick='Billing.save()']")
    private WebElement billingContinueBtn;

    // ------------------- Step 2: Shipping Address -------------------
    @FindBy(xpath = "//input[@onclick='Shipping.save()']")
    private WebElement shippingContinueBtn;

    // ------------------- Step 3: Shipping Method -------------------
    @FindBy(name = "shippingoption")
    private List<WebElement> shippingMethodOptions;

    @FindBy(xpath = "//input[@onclick='ShippingMethod.save()']")
    private WebElement shippingMethodContinueBtn;

    // ------------------- Step 4: Payment Method -------------------
    @FindBy(name = "paymentmethod")
    private List<WebElement> paymentMethodOptions;

    @FindBy(xpath = "//input[@onclick='PaymentMethod.save()']")
    private WebElement paymentMethodContinueBtn;

    // ------------------- Step 5: Payment Info -------------------
    @FindBy(xpath = "//input[@onclick='PaymentInfo.save()']")
    private WebElement paymentInfoContinueBtn;

    // ------------------- Step 6: Confirm Order -------------------
    @FindBy(xpath = "//input[@onclick='ConfirmOrder.save()']")
    private WebElement confirmOrderBtn;

    @FindBy(xpath = "//strong[contains(text(),'successfully processed')]")
    private WebElement orderConfirmationMessage;

    // ---- CONSTRUCTOR ----
    public CheckoutPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        logger.info("CheckoutPage initialized with driver: {}", driver);
    }

    // ------------------- Actions -------------------

    /** Select “New Address” option in billing dropdown */
    public void selectNewAddress() {
        logger.info("Selecting 'New Address' option in billing dropdown");
        Select select = new Select(waitUtils.waitForElementVisible(addressDropdown));
        select.selectByVisibleText("New Address");
    }

    /** Fill billing address fields */
    public void fillBillingAddress(String fName, String lName, String email,
                                   String country, String city,
                                   String addr1, String addr2,
                                   String zip, String phone) {
        logger.info("Filling billing address for user: {} {}", fName, lName);
        waitUtils.clearAndEnterText(billingFirstName, fName);
        waitUtils.clearAndEnterText(billingLastName, lName);
        waitUtils.clearAndEnterText(billingEmail, email);

        new Select(billingCountry).selectByVisibleText(country);
        logger.info("Selected billing country: {}", country);

        waitUtils.clearAndEnterText(billingCity, city);
        waitUtils.clearAndEnterText(billingAddress1, addr1);
        waitUtils.clearAndEnterText(billingAddress2, addr2);
        waitUtils.clearAndEnterText(billingZip, zip);
        waitUtils.clearAndEnterText(billingPhone, phone);
    }

    public void continueBilling() {
        logger.info("Continuing from Billing step");
        waitUtils.clickElement(billingContinueBtn);
    }

    public void continueShippingAddress() {
        logger.info("Continuing from Shipping Address step");
        waitUtils.clickElement(shippingContinueBtn);
    }

    /** Select shipping method by value attribute */
    public void selectShippingMethod(String value) {
        logger.info("Selecting shipping method: {}", value);
        for (WebElement m : shippingMethodOptions) {
            if (m.getAttribute("value").equalsIgnoreCase(value)) {
                m.click();
                logger.info("Shipping method selected: {}", value);
                break;
            }
        }
    }

    public void continueShippingMethod() {
        logger.info("Continuing from Shipping Method step");
        waitUtils.clickElement(shippingMethodContinueBtn);
    }

    /** Select payment method by value attribute */
    public void selectPaymentMethod(String value) {
        logger.info("Selecting payment method: {}", value);
        for (WebElement p : paymentMethodOptions) {
            if (p.getAttribute("value").equalsIgnoreCase(value)) {
                p.click();
                logger.info("Payment method selected: {}", value);
                break;
            }
        }
    }

    public void continuePaymentMethod() {
        logger.info("Continuing from Payment Method step");
        waitUtils.clickElement(paymentMethodContinueBtn);
    }

    public void continuePaymentInfo() {
        logger.info("Continuing from Payment Info step");
        waitUtils.clickElement(paymentInfoContinueBtn);
    }

    public void confirmOrder() {
        logger.info("Confirming order");
        waitUtils.clickElement(confirmOrderBtn);
    }

    /** Verify if order confirmation message is displayed */
    public boolean isOrderConfirmed() {
        try {
            boolean confirmed = waitUtils.waitForElementVisible(orderConfirmationMessage).isDisplayed();
            logger.info("Order confirmation status: {}", confirmed);
            return confirmed;
        } catch (Exception e) {
            logger.error("Failed to verify order confirmation", e);
            return false;
        }
    }
}