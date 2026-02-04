package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.DriverManager;
import ui.pages.*;
import uiApi.utilities.RandomDataUtils;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckoutPageTest extends AuthenticatedBasePage {

    private static final Logger logger = LogManager.getLogger(CheckoutPageTest.class);

    @Test(groups = {"Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void testCompleteCheckoutFlow() throws Exception {
        logger.info("Starting test: testCompleteCheckoutFlow");

        CartPage cartPage = new CartPage(DriverManager.getDriver());
        logger.info("Navigating to Cart page");
        cartPage.goToCartPage();

        logger.info("Agreeing to terms of service");
        cartPage.agreeToTerms();

        logger.info("Clicking Checkout button");
        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());

        // Step 1: Billing
        logger.info("Selecting 'New Address' option");
        checkoutPage.selectNewAddress();

        logger.info("Filling billing address with random test data");
        checkoutPage.fillBillingAddress(
                RandomDataUtils.randomText(),      // First name
                RandomDataUtils.randomText(),      // Last name
                RandomDataUtils.randomEmail(),     // Email
                "United States",                   // Country
                RandomDataUtils.randomText(),      // City
                RandomDataUtils.randomText(),      // Address1
                RandomDataUtils.randomText(),      // Address2
                RandomDataUtils.randomZip(),       // Zip
                RandomDataUtils.randomPhone()      // Phone
        );
        checkoutPage.continueBilling();

        // Step 2: Shipping Address
        logger.info("Continuing from Shipping Address step");
        checkoutPage.continueShippingAddress();

        // Step 3: Shipping Method
        logger.info("Selecting shipping method: Next Day Air (0.00)");
        checkoutPage.selectShippingMethod("Next Day Air (0.00)");
        checkoutPage.continueShippingMethod();

        // Step 4: Payment Method
        logger.info("Selecting payment method: Cash On Delivery (COD) (7.00)");
        checkoutPage.selectPaymentMethod("Cash On Delivery (COD) (7.00)");
        checkoutPage.continuePaymentMethod();

        // Step 5: Payment Information
        logger.info("Continuing from Payment Information step");
        checkoutPage.continuePaymentInfo();

        // Step 6: Confirm Order
        logger.info("Confirming order");
        checkoutPage.confirmOrder();

        // Verify order confirmation
        logger.info("Verifying order confirmation message");
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order not confirmed");
        logger.info("Order confirmed successfully");

        logger.info("Test completed: testCompleteCheckoutFlow");
    }
}