package com.demoshopp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import com.demoshopp.pageutilities.WaitUtils;

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
        PageFactory.initElements(driver, this); // initialize all @FindBy elements
    }

    // ---- ACTION METHODS ----
    public void clickCategory(String categoryName) {
        for (WebElement category : waitUtils.waitForAllVisible(categoryLinks)) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                waitUtils.clickElement(category);
                return;
            }
        }
        throw new RuntimeException("Category not found: " + categoryName);
    }

    public List<String> getSubCategoryNames() {
        return waitUtils.waitForAllVisible(subCategoryItems)
                        .stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList());
    }

    public void clickSubCategory(String subCategoryName) {
        for (WebElement subCategory : waitUtils.waitForAllVisible(subCategoryItems)) {
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
