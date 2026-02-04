package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.Basepage;
import ui.base.DriverManager;
import ui.pages.CategoryPage;
import ui.pages.ProductListPage;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductListPageTest extends Basepage {

    private static final Logger logger = LogManager.getLogger(ProductListPageTest.class);

    @Test(groups = {"Smoke","Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void testProductListAndNavigation() {
        logger.info("Starting test: testProductListAndNavigation");

        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());
        ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());

        // Step 1: Navigate to Desktops
        logger.info("Navigating to category: Computers");
        categoryPage.clickCategory("Computers");
        logger.info("Navigating to subcategory: Desktops");
        categoryPage.clickSubCategory("Desktops");

        // Step 2: Verify products are visible
        logger.info("Verifying products are visible in Desktops");
        Assert.assertTrue(productListPage.areProductsVisible(), "No products found in Desktops");
        logger.info("Products are visible in Desktops");

        // Step 3: Click on a product
        String productName = "Build your own computer";
        logger.info("Clicking on product: {}", productName);
        productListPage.clickOnProductByName(productName);

        // Step 4: Verify navigation to product page
        logger.info("Verifying navigation to product page: {}", productName);
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("build-your-own-computer"),
                          "Product page not loaded");
        logger.info("Successfully navigated to product page: {}", productName);

        logger.info("Test completed: testProductListAndNavigation");
    }
}