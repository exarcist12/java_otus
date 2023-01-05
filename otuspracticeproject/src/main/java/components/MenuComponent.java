package components;

import annotations.Path;
import data.CategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CategoryPage;
import waiters.StandartWaiter;


public class MenuComponent extends AbsBaseComponent<MenuComponent> {

    public MenuComponent(WebDriver driver) {
        super(driver);
    }

    private final String menuItemByTitleSelectorTemplate = "#categories_id a[title=\"%s\"]";

    public CategoryPage clickCategory(CategoryData categoryData) {
        String selector = String.format(menuItemByTitleSelectorTemplate, categoryData.getName());

        driver.findElement(By.cssSelector(selector)).click();

        return new CategoryPage(driver);
    }

    public MenuComponent menuItemActive(CategoryData categoryData) {
        String selector = String.format(menuItemByTitleSelectorTemplate, categoryData.getName());
        StandartWaiter standartWaiter = new StandartWaiter(driver);

        return this;
    }

}
