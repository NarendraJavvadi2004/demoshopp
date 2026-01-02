package com.demoshopp.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.demoshopp.pageutilities.WaitUtils;

public class CheckoutPage {

    // ✅ immutable once set
    private final WaitUtils waitUtils;  // ✅ immutable once set

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
    }

    // ------------------- Actions -------------------

    /** Select “New Address” option in billing dropdown */
    public void selectNewAddress() {
        Select select = new Select(waitUtils.waitForElementVisible(addressDropdown));
        select.selectByVisibleText("New Address");
    }

    /** Fill billing address fields */
    public void fillBillingAddress(String fName, String lName, String email,
                                   String country, String city,
                                   String addr1, String addr2,
                                   String zip, String phone) {

        waitUtils.clearAndEnterText(billingFirstName, fName);
        waitUtils.clearAndEnterText(billingLastName, lName);
        waitUtils.clearAndEnterText(billingEmail, email);

        new Select(billingCountry).selectByVisibleText(country);

        waitUtils.clearAndEnterText(billingCity, city);
        waitUtils.clearAndEnterText(billingAddress1, addr1);
        waitUtils.clearAndEnterText(billingAddress2, addr2);
        waitUtils.clearAndEnterText(billingZip, zip);
        waitUtils.clearAndEnterText(billingPhone, phone);
    }

    public void continueBilling() {
        waitUtils.clickElement(billingContinueBtn);
    }

    public void continueShippingAddress() {
        waitUtils.clickElement(shippingContinueBtn);
    }

    /** Select shipping method by value attribute */
    public void selectShippingMethod(String value) {
        for (WebElement m : shippingMethodOptions) {
            if (m.getAttribute("value").equalsIgnoreCase(value)) {
                m.click();
                break;
            }
        }
    }

    public void continueShippingMethod() {
        waitUtils.clickElement(shippingMethodContinueBtn);
    }

    /** Select payment method by value attribute */
    public void selectPaymentMethod(String value) {
        for (WebElement p : paymentMethodOptions) {
            if (p.getAttribute("value").equalsIgnoreCase(value)) {
                p.click();
                break;
            }
        }
    }

    public void continuePaymentMethod() {
        waitUtils.clickElement(paymentMethodContinueBtn);
    }

    public void continuePaymentInfo() {
        waitUtils.clickElement(paymentInfoContinueBtn);
    }

    public void confirmOrder() {
        waitUtils.clickElement(confirmOrderBtn);
    }

    /** Verify if order confirmation message is displayed */
    public boolean isOrderConfirmed() {
        try {
            return waitUtils.waitForElementVisible(orderConfirmationMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	
}