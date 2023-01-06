package components;

import data.CategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import waiters.StandartWaiter;


public class MenuComponent extends AbsBaseComponent<MenuComponent> {

    public MenuComponent(WebDriver driver) {
        super(driver);
    }

    StandartWaiter standartWaiter = new StandartWaiter(driver);
    private final String menuItemByTitleSelectorTemplate = "#categories_id a[title=\"%s\"]";
    private final String catalogCategoriesCheckboxesSelectorTemplate = "//label[text()=\"%s\"]/..//div/input[@checked]";

    public CategoryPage clickCategory(CategoryData categoryData) {
        String selector = String.format(menuItemByTitleSelectorTemplate, categoryData.getName());

        driver.findElement(By.cssSelector(selector)).click();

        return new CategoryPage(driver);
    }

    public MenuComponent menuItemActive(CategoryData categoryData) {
        String selector = String.format(catalogCategoriesCheckboxesSelectorTemplate, categoryData.getName());
        WebElement element = driver.findElement(By.xpath(selector));

        assert standartWaiter.waitForElementVisible(element): "Error";




        return this;
    }

}
