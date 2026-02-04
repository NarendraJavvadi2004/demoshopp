package ui.tests;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.HomePage;
import uiApi.utilities.RetryAnalyzer;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HomePageTest extends Basepage {

    private static final Logger logger = LogManager.getLogger(HomePageTest.class);

    @Test(groups = {"Smoke","Sanity","Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void verifyHomePageLinksAreWorking() {
        logger.info("Starting test: verifyHomePageLinksAreWorking");

        HomePage home = new HomePage(DriverManager.getDriver());

        // Initially user is logged out → login link should be visible
        logger.info("Checking if user is logged out");
        Assert.assertTrue(home.isUserLoggedOut(), "Login link should be visible for logged-out user");
        logger.info("Login link is visible for logged-out user");

        // Click Login link to check navigation works
        logger.info("Clicking Login link");
        home.clickLogin();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("login"), "Login page URL mismatch!");
        logger.info("Navigated to Login page successfully");

        // Click Register link to check navigation works
        logger.info("Clicking Register link");
        home.clickRegister();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("register"), "Register page URL mismatch!");
        logger.info("Navigated to Register page successfully");

        // Check Cart navigation
        logger.info("Clicking Cart link");
        home.openCart();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("cart"), "Cart page URL mismatch!");
        logger.info("Navigated to Cart page successfully");

        // Wishlist navigation
        logger.info("Clicking Wishlist link");
        home.openWishlist();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("wishlist"), "Wishlist page URL mismatch!");
        logger.info("Navigated to Wishlist page successfully");

        logger.info("Test completed: verifyHomePageLinksAreWorking");
    }
}