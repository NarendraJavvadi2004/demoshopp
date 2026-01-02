package com.demoshopp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import com.demoshopp.pageutilities.WaitUtils;

public class ProductListPage {

    private WaitUtils waitUtils;

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(css = ".product-item")
    private List<WebElement> productItems;

    @FindBy(css = ".product-item .product-title a")
    private List<WebElement> productTitles;

    // ---- CONSTRUCTOR ----
    public ProductListPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);  // Initialize WaitUtils
        PageFactory.initElements(driver, this);  // Initialize @FindBy elements
    }

    // ---- ACTIONS ----
    public boolean areProductsVisible() {
        return !waitUtils.waitForAllVisible(productItems).isEmpty();
    }

    public List<WebElement> getAllProductTitles() {
        return waitUtils.waitForAllVisible(productTitles);
    }

    public void clickOnProductByName(String productName) {
        for (WebElement product : waitUtils.waitForAllVisible(productTitles)) {
            if (product.getText().equalsIgnoreCase(productName)) {
                waitUtils.clickElement(product);
                return;
            }
        }
        throw new RuntimeException("Product not found: " + productName);
    }
}
