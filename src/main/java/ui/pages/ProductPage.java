package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPage {

    private WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(ProductPage.class);

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(xpath = "//div[@class='product-name']/h1")
    private WebElement productTitle;

    @FindBy(xpath = "//div[@class='product-price']//span")
    private WebElement productPrice;

    @FindBy(xpath = "//div[@class='full-description']")
    private WebElement productDescription;

    @FindBy(id = "add-to-cart-button-16")
    private WebElement addToCartButton;

    @FindBy(xpath = "//p[contains(text(),'The product has been added')]")
    private WebElement successMessage;

    // Example HDD option (optional product attribute)
    @FindBy(id = "product_attribute_16_3_6_19")
    private WebElement hddOption;

    // ---- CONSTRUCTOR ----
    public ProductPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this); // initialize all @FindBy elements
        logger.info("ProductPage initialized with driver: {}", driver);
    }

    // ---- ACTION METHODS ----
    public String getProductTitle() {
        String title = waitUtils.waitForElementVisible(productTitle).getText();
        logger.info("Retrieved product title: {}", title);
        return title;
    }

    public String getProductPrice() {
        String price = waitUtils.waitForElementVisible(productPrice).getText();
        logger.info("Retrieved product price: {}", price);
        return price;
    }

    public String getProductDescription() {
        String description = waitUtils.waitForElementVisible(productDescription).getText();
        logger.info("Retrieved product description: {}", description);
        return description;
    }

    // Select HDD if required
    public void selectHDDIfRequired() {
        try {
            if (hddOption.isDisplayed() && !hddOption.isSelected()) {
                logger.info("Selecting HDD option");
                hddOption.click();
            } else {
                logger.debug("HDD option not required or already selected");
            }
        } catch (Exception ignored) {
            logger.debug("HDD option not available for this product");
        }
    }

    // Add to cart
    public void addToCart() {
        logger.info("Attempting to add product to cart");
        selectHDDIfRequired(); // ✅ Important step: select HDD first
        waitUtils.clickElement(addToCartButton);
        logger.info("Clicked Add to Cart button");
    }

    public boolean isProductAddedToCart() {
        try {
            boolean added = waitUtils.waitForElementVisible(successMessage)
                                     .getText()
                                     .contains("added to your shopping cart");
            logger.info("Product added to cart status: {}", added);
            return added;
        } catch (Exception e) {
            logger.error("Failed to verify product added to cart", e);
            return false;
        }
    }
}