package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.DriverManager;
import ui.pages.*;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPageTest extends AuthenticatedBasePage {

    private static final Logger logger = LogManager.getLogger(ProductPageTest.class);

    @Test(groups = {"Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void testProductDetailsAndAddToCart() {
        logger.info("Starting test: testProductDetailsAndAddToCart");

        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());
        ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());
        ProductPage productPage = new ProductPage(DriverManager.getDriver());

        // Step 1: Navigate
        logger.info("Navigating to category: Computers");
        categoryPage.clickCategory("Computers");
        logger.info("Navigating to subcategory: Desktops");
        categoryPage.clickSubCategory("Desktops");

        // Step 2: Click product
        String productName = "Build your own computer";
        logger.info("Clicking on product: {}", productName);
        productListPage.clickOnProductByName(productName);

        // Step 3: Validations
        logger.info("Validating product details");
        Assert.assertTrue(productPage.getProductTitle().contains("computer"), "Title mismatch");
        logger.info("Product title contains 'computer'");
        Assert.assertFalse(productPage.getProductPrice().isEmpty(), "Price missing");
        logger.info("Product price is displayed");
        Assert.assertFalse(productPage.getProductDescription().isEmpty(), "Description missing");
        logger.info("Product description is displayed");

        // Step 4: Add to cart
        logger.info("Adding product to cart");
        productPage.addToCart();

        // Step 5: Verify popup
        logger.info("Verifying product added to cart popup");
        Assert.assertTrue(productPage.isProductAddedToCart(), "Add to cart popup not found");
        logger.info("Product successfully added to cart");

        logger.info("Test completed: testProductDetailsAndAddToCart");
    }
}