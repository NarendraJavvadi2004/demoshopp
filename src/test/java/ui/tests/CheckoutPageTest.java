package ui.tests;
import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.DriverManager;
import ui.pages.*;
import uiApi.utilities.RandomDataUtils;
import uiApi.utilities.RetryAnalyzer;
public class CheckoutPageTest extends AuthenticatedBasePage {

	 
    @Test(groups = {"Regression"},
    retryAnalyzer = RetryAnalyzer.class)
    public void testCompleteCheckoutFlow() throws Exception {
    	
    	CartPage cartPage = new CartPage(DriverManager.getDriver());
        cartPage.goToCartPage();       // <-- add this
        cartPage.agreeToTerms();       // <-- add this
        cartPage.clickCheckout();      // <-- add this

        CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
           
        
     checkoutPage.selectNewAddress();
        // Step 1: Billing
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
        checkoutPage.continueShippingAddress();

        // Step 3: Shipping Method
        checkoutPage.selectShippingMethod("Next Day Air (0.00)");
        checkoutPage.continueShippingMethod();

        // Step 4: Payment Method
        checkoutPage.selectPaymentMethod("Cash On Delivery (COD) (7.00)");
        checkoutPage.continuePaymentMethod();

        // Step 5: Payment Information
        checkoutPage.continuePaymentInfo();

        // Step 6: Confirm Order
        checkoutPage.confirmOrder();

        // Verify order confirmation
        Assert.assertTrue(checkoutPage.isOrderConfirmed(), "Order not confirmed");
    }
}