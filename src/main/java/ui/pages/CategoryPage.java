package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiApi.utilities.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage {

    private WaitUtils waitUtils;

    // ---- LOCATORS USING PAGEFACTORY ----
    @FindBy(css = "ul.top-menu > li > a")
    private List<WebElement> categoryLinks;

    @FindBy(css = ".sub-category-item")
    private List<WebElement> subCategoryItems;

    // ---- CONSTRUCTOR ----
    public CategoryPage(WebDriver driver) {
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    // ---- ACTION METHODS ----
    public void clickCategory(String categoryName) {
        List<WebElement> categories = waitUtils.waitForAllVisible(categoryLinks);

        for (WebElement category : categories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                waitUtils.clickElement(category);
                return;
            }
        }
        throw new RuntimeException("Category not found: " + categoryName);
    }

    public List<String> getSubCategoryNames() {
        List<String> subCategoryNames = new ArrayList<>();
        List<WebElement> subCategories = waitUtils.waitForAllVisible(subCategoryItems);

        for (WebElement subCategory : subCategories) {
            subCategoryNames.add(subCategory.getText());
        }
        return subCategoryNames;
    }

    public void clickSubCategory(String subCategoryName) {
        List<WebElement> subCategories = waitUtils.waitForAllVisible(subCategoryItems);

        for (WebElement subCategory : subCategories) {
            if (subCategory.getText().equalsIgnoreCase(subCategoryName)) {
                waitUtils.clickElement(subCategory);
                return;
            }
        }
        throw new RuntimeException("Subcategory not found: " + subCategoryName);
    }

    public boolean areSubCategoriesVisible() {
        return !waitUtils.waitForAllVisible(subCategoryItems).isEmpty();
    }
}
