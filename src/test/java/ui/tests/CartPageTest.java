package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.DriverManager;
import ui.pages.*;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartPageTest extends AuthenticatedBasePage {

    private static final Logger logger = LogManager.getLogger(CartPageTest.class);

    @Test(groups = {"Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void testCartPageContents() {
        logger.info("Starting test: testCartPageContents");

        CartPage cartPage = new CartPage(DriverManager.getDriver());

        // Step 0: Open Cart Page first
        logger.info("Navigating to Cart page");
        cartPage.goToCartPage();

        // Step 1: Verify cart is visible
        logger.info("Verifying cart visibility");
        Assert.assertTrue(cartPage.isCartVisible(), "Cart is not visible or empty");
        logger.info("Cart is visible and contains items");

        // Step 2: Verify product names are listed
        logger.info("Verifying product names are listed in cart");
        Assert.assertTrue(cartPage.getProductNames().size() > 0, "No products found in cart");
        logger.info("Product names are listed in cart");

        // Step 3: Verify total price is displayed
        String total = cartPage.getTotalPrice();
        logger.info("Retrieved total price: {}", total);
        Assert.assertTrue(total.length() > 0, "Total price not displayed");
        logger.info("Total price is displayed");

        // Step 4: Agree to terms of service
        logger.info("Agreeing to terms of service");
        cartPage.agreeToTerms();

        // Step 5: Click checkout
        logger.info("Clicking Checkout button");
        cartPage.clickCheckout();

        // Step 6: Skip checkout page navigation assertion
        logger.info("Checkout button clicked. Proceeding to next page...");

        logger.info("Test completed: testCartPageContents");
    }
}