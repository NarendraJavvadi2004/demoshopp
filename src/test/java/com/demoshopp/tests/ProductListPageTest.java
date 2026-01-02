package com.demoshopp.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoshopp.pages.CategoryPage;
import com.demoshopp.pages.ProductListPage;
import com.demoshopp.pageutilities.RetryAnalyzer;
import com.demoshopp.base.Basepage;
import com.demoshopp.base.DriverManager;

public class ProductListPageTest extends Basepage {

    @Test(groups = {"Smoke","Regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void testProductListAndNavigation() {
        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());
        ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());

        // Step 1: Navigate to Desktops
        categoryPage.clickCategory("Computers");
        categoryPage.clickSubCategory("Desktops");

        // Step 2: Verify products are visible
        Assert.assertTrue(productListPage.areProductsVisible(), "No products found in Desktops");

        // Step 3: Click on a product
        productListPage.clickOnProductByName("Build your own computer");

        // Step 4: Verify navigation to product page
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("build-your-own-computer"), "Product page not loaded");
    }
}