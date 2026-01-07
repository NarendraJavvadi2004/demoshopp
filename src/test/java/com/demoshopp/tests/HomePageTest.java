
package com.demoshopp.tests;

import com.demoshopp.base.Basepage;
import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.HomePage;
import com.demoshopp.pageutilities.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends Basepage {

    @Test(groups = {"Smoke","Sanity","Regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void verifyHomePageLinksAreWorking() {

        HomePage home = new HomePage(DriverManager.getDriver());

        // Initially user is logged out → login link should be visible
        Assert.assertTrue(home.isUserLoggedOut(), "Login link should be visible for logged-out user");

        // Click Login link to check navigation works
        home.clickLogin();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Login page URL mismatch!");


        // Click Register link to check navigation works
        home.clickRegister();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("register"), "Register page URL mismatch!");

        // Check Cart navigation
        home.openCart();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("cart"), "Cart page URL mismatch!");

    

        // Wishlist navigation
        home.openWishlist();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("wishlist"), "Wishlist page URL mismatch!");

      
    }
}
