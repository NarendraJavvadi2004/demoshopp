package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

import java.util.List;

public class CartPage {

    
    private WaitUtils waitUtils;   // ✅ consistent naming

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
    }

    // ---- ACTIONS ----
    public void goToCartPage() {
        waitUtils.clickElement(cartIcon);
    }

    public boolean isCartVisible() {
        return !cartItemRows.isEmpty();
    }

    public List<WebElement> getProductNames() {
        return productNames;
    }

    public String getTotalPrice() {
        return waitUtils.getElementText(totalPrice);
    }

    public void agreeToTerms() {
        waitUtils.clickElement(termsCheckbox);
    }

    public void clickCheckout() {
        waitUtils.clickElement(checkoutButton);
    }

	
}