package com.demoshopp.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoshopp.base.DriverManager;
import com.demoshopp.pages.*;
import com.demoshopp.pageutilities.RetryAnalyzer;

public class ProductPageTest extends AuthenticatedBasePage {
    @Test(groups = {"Regression"},
    	retryAnalyzer = RetryAnalyzer.class)
    public void testProductDetailsAndAddToCart() {

        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());
        ProductListPage productListPage = new ProductListPage(DriverManager.getDriver());
        ProductPage productPage = new ProductPage(DriverManager.getDriver());

        // Step 1: Navigate
        categoryPage.clickCategory("Computers");
        categoryPage.clickSubCategory("Desktops");

        // Step 2: Click product
        productListPage.clickOnProductByName("Build your own computer");

        // Step 3: Validations
        Assert.assertTrue(productPage.getProductTitle().contains("computer"), "Title mismatch");
        Assert.assertFalse(productPage.getProductPrice().isEmpty(), "Price missing");
        Assert.assertFalse(productPage.getProductDescription().isEmpty(), "Description missing");

        // Step 4: Add to cart
        productPage.addToCart();

        // Step 5: Verify popup
        Assert.assertTrue(productPage.isProductAddedToCart(), "Add to cart popup not found");
    }
}
