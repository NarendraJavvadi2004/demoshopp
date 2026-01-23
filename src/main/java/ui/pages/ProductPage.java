package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

public class ProductPage {

    private WaitUtils waitUtils;

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
    }

    // ---- ACTION METHODS ----
    public String getProductTitle() {
        return waitUtils.waitForElementVisible(productTitle).getText();
    }

    public String getProductPrice() {
        return waitUtils.waitForElementVisible(productPrice).getText();
    }

    public String getProductDescription() {
        return waitUtils.waitForElementVisible(productDescription).getText();
    }

    // Select HDD if required
    public void selectHDDIfRequired() {
        try {
            if (hddOption.isDisplayed() && !hddOption.isSelected()) {
                hddOption.click();
            }
        } catch (Exception ignored) {
            // HDD not required for other products → ignore
        }
    }

    // Add to cart
    public void addToCart() {
        selectHDDIfRequired(); // ✅ Important step: select HDD first
        waitUtils.clickElement(addToCartButton);
    }

    public boolean isProductAddedToCart() {
        try {
            return waitUtils.waitForElementVisible(successMessage)
                            .getText()
                            .contains("added to your shopping cart");
        } catch (Exception e) {
            return false;
        }
    }

}