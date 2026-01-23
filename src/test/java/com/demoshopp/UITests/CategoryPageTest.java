package com.demoshopp.UITests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.demoshopp.pages.*;
import com.demoshopp.pageutilities.RetryAnalyzer;
import com.demoshopp.base.*;

public class CategoryPageTest extends Basepage {

    @Test(groups = {"Smoke","Regression"},
    	retryAnalyzer = RetryAnalyzer.class)
    public void testCategoryToSubcategoryFlow() {
        CategoryPage categoryPage = new CategoryPage(DriverManager.getDriver());

        // Step 1: Click on "Computers"
        categoryPage.clickCategory("Computers");

        // Step 2: Verify subcategories are visible
        Assert.assertTrue(categoryPage.areSubCategoriesVisible(), "Subcategories not visible");

        // Step 3: Click on "Desktops"
        categoryPage.clickSubCategory("Desktops");

        // Step 4: Verify product list page is loaded (next step in your flow)
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("desktops"), "Not navigated to Desktops page");
    }
 
  }
