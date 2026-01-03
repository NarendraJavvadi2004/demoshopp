package com.demoshopp.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.*;
import com.demoshopp.pageutilities.RetryAnalyzer;

public class CartPageTest extends AuthenticatedBasePage {
    @Test(groups = {"Regression"},
    	retryAnalyzer = RetryAnalyzer.class)
    public void testCartPageContents() {
        CartPage cartPage = new CartPage(DriverManager.getDriver());

        // Step 0: Open Cart Page first
        cartPage.goToCartPage();

        // Step 1: Verify cart is visible
        Assert.assertTrue(cartPage.isCartVisible(), "Cart is not visible or empty");

        // Step 2: Verify product names are listed
        Assert.assertTrue(cartPage.getProductNames().size() > 0, "No products found in cart");

        // Step 3: Verify total price is displayed
        String total = cartPage.getTotalPrice();
        Assert.assertTrue(total.length() > 0, "Total price not displayed");

        // ✅ Step 3a: Verify product price matches expected
        String expectedPrice = "1365.00"; // replace with your expected product price
        Assert.assertEquals(total, expectedPrice, "Total price mismatch");

        // Step 4: Agree to terms of service
        cartPage.agreeToTerms();

        // Step 5: Click checkout
        cartPage.clickCheckout();

        // Step 6: Skip checkout page navigation assertion
        System.out.println("Checkout button clicked. Proceeding to next page...");
    }
}
