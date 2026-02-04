package ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ui.base.*;
import ui.pages.*;
import uiApi.utilities.RetryAnalyzer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryPageTest extends Basepage {

    private static final Logger logger = LogManager.getLogger(CategoryPageTest.class);

    @Test(groups = {"Smoke","Regression"},
          retryAnalyzer = RetryAnalyzer.class)
    public void testCategoryToSubcategoryFlow() {
        logger.info("Starting test: testCategoryToSubcategoryFlow");

        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());

        // Step 1: Click on "Computers"
        logger.info("Clicking on category: Computers");
        categoryPage.clickCategory("Computers");

        // Step 2: Verify subcategories are visible
        logger.info("Verifying subcategories are visible");
        Assert.assertTrue(categoryPage.areSubCategoriesVisible(), "Subcategories not visible");
        logger.info("Subcategories are visible");

        // Step 3: Click on "Desktops"
        logger.info("Clicking on subcategory: Desktops");
        categoryPage.clickSubCategory("Desktops");

        // Step 4: Verify product list page is loaded
        logger.info("Verifying navigation to Desktops page");
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("desktops"), "Not navigated to Desktops page");
        logger.info("Successfully navigated to Desktops page");

        logger.info("Test completed: testCategoryToSubcategoryFlow");
    }
}