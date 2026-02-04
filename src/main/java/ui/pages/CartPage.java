package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CartPage {

    private WaitUtils waitUtils;   // ✅ consistent naming
    private static final Logger logger = LogManager.getLogger(CartPage.class);

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(xpath = "//tr[@class='cart-item-row']")
    private List<WebElement> cartItemRows;

    @FindBy(xpath = "//tr[@class='cart-item-row']/td[@class='product']/a")
    private List<WebElement> productNames;

    @FindBy(xpath = "//span[@class='product-subtotal']")
    private WebElement totalPrice;

    @FindBy(id = "termsofservice")
    private WebElement termsCheckbox;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//span[text()='Shopping cart']")
    private WebElement cartIcon;

    // ---- CONSTRUCTOR ----
    public CartPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this); // initialize all @FindBy elements
        logger.info("CartPage initialized with driver: {}", driver);
    }

    // ---- ACTIONS ----
    public void goToCartPage() {
        logger.info("Navigating to Cart page");
        waitUtils.clickElement(cartIcon);
    }

    public boolean isCartVisible() {
        boolean visible = !cartItemRows.isEmpty();
        logger.info("Cart visibility status: {}", visible);
        return visible;
    }

    public List<WebElement> getProductNames() {
        logger.info("Retrieving product names from cart. Count: {}", productNames.size());
        return productNames;
    }

    public String getTotalPrice() {
        String price = waitUtils.getElementText(totalPrice);
        logger.info("Retrieved total cart price: {}", price);
        return price;
    }

    public void agreeToTerms() {
        logger.info("Agreeing to terms of service");
        waitUtils.clickElement(termsCheckbox);
    }

    public void clickCheckout() {
        logger.info("Clicking Checkout button");
        waitUtils.clickElement(checkoutButton);
    }
}