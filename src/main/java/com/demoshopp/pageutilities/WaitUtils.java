package com.demoshopp.pageutilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriverWait wait;

    // Default constructor with 10s timeout
    public WaitUtils(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // 🔹 Wait for WebElement directly
    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // 🔹 Actions
    public void clickElement(WebElement element) {
        waitForClickable(element).click();
    }

    public void enterText(WebElement element, String text) {
        waitForElementVisible(element).sendKeys(text);
    }

    public void clearAndEnterText(WebElement element, String text) {
        WebElement el = waitForElementVisible(element);
        el.clear();
        el.sendKeys(text);
    }
    public List<WebElement> waitForAllVisible(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public String getElementText(WebElement element) {
        return waitForElementVisible(element).getText();
    }
}