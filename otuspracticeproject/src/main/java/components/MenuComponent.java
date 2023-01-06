package components;

import data.CategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CategoryPage;
import waiters.StandartWaiter;

import java.util.List;

import static waiters.WaiterInt.IMPLICITLY_WAIT_SECOND;


public class MenuComponent extends AbsBaseComponent<MenuComponent> {

    public MenuComponent(WebDriver driver) {
        super(driver);
    }


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
//        element.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, IMPLICITLY_WAIT_SECOND);
        boolean condPerf;
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            condPerf = true;
        } catch (Exception ex) {
            condPerf =  false;
        }
//       assert standartWaiter.waitForElementVisible(element): "Error";
        return this;
    }

}
