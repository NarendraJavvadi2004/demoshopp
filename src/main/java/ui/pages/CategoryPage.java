package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage {

    private WaitUtils waitUtils;
    private static final Logger logger = LogManager.getLogger(CategoryPage.class);

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(css = "ul.top-menu > li > a")
    private List<WebElement> categoryLinks;

    @FindBy(css = ".sub-category-item")
    private List<WebElement> subCategoryItems;

    // ---- CONSTRUCTOR ----
    public CategoryPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
        logger.info("CategoryPage initialized with driver: {}", driver);
    }

    // ---- ACTION METHODS ----
    public void clickCategory(String categoryName) {
        logger.info("Attempting to click category: {}", categoryName);
        List<WebElement> categories = waitUtils.waitForAllVisible(categoryLinks);

        for (WebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                logger.info("Category found: {}. Clicking...", categoryName);
                waitUtils.clickElement(category);
                return;
            }
        }
        logger.error("Category not found: {}", categoryName);
        throw new RuntimeException("Category not found: " + categoryName);
    }

    public List<String> getSubCategoryNames() {
        logger.info("Fetching subcategory names");
        List<String> subCategoryNames = new ArrayList<>();
        List<WebElement> subCategories = waitUtils.waitForAllVisible(subCategoryItems);

        for (WebElement subCategory : subCategories) {
            subCategoryNames.add(subCategory.getText());
        }
        logger.info("Subcategories retrieved: {}", subCategoryNames);
        return subCategoryNames;
    }

    public void clickSubCategory(String subCategoryName) {
        logger.info("Attempting to click subcategory: {}", subCategoryName);
        List<WebElement> subCategories = waitUtils.waitForAllVisible(subCategoryItems);

        for (WebElement subCategory : subCategories) {
            if (subCategory.getText().equalsIgnoreCase(subCategoryName)) {
                logger.info("Subcategory found: {}. Clicking...", subCategoryName);
                waitUtils.clickElement(subCategory);
                return;
            }
        }
        logger.error("Subcategory not found: {}", subCategoryName);
        throw new RuntimeException("Subcategory not found: " + subCategoryName);
    }

    public boolean areSubCategoriesVisible() {
        boolean visible = !waitUtils.waitForAllVisible(subCategoryItems).isEmpty();
        logger.info("Subcategories visible: {}", visible);
        return visible;
    }
}