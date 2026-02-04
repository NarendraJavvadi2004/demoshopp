package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductListPage {

    private WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(ProductListPage.class);

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(css = ".product-item")
    private List<WebElement> productItems;

    @FindBy(css = ".product-item .product-title a")
    private List<WebElement> productTitles;

    // ---- CONSTRUCTOR ----
    public ProductListPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);  // Initialize WaitUtils
        PageFactory.initElements(driver, this);  // Initialize @FindBy elements
        logger.info("ProductListPage initialized with driver: {}", driver);
    }

    // ---- ACTIONS ----
    public boolean areProductsVisible() {
        boolean visible = !waitUtils.waitForAllVisible(productItems).isEmpty();
        logger.info("Products visible: {}", visible);
        return visible;
    }

    public List<WebElement> getAllProductTitles() {
        List<WebElement> titles = waitUtils.waitForAllVisible(productTitles);
        logger.info("Retrieved product titles count: {}", titles.size());
        return titles;
    }

    public void clickOnProductByName(String productName) {
        logger.info("Attempting to click product: {}", productName);
        for (WebElement product : waitUtils.waitForAllVisible(productTitles)) {
            if (product.getText().equalsIgnoreCase(productName)) {
                logger.info("Product found: {}. Clicking...", productName);
                waitUtils.clickElement(product);
                return;
            }
        }
        logger.error("Product not found: {}", productName);
        throw new RuntimeException("Product not found: " + productName);
    }
}