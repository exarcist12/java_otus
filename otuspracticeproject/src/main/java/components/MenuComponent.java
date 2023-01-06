package components;

import data.CategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import waiters.StandartWaiter;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        List<WebElement> elements = driver.findElements(By.xpath(selector));
        assertThat("error", standartWaiter.waitForElementVisible(element), equalTo(true));
        return this;
    }

}
