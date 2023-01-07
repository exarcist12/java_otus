package components;

import data.CategoryData;
import data.CoursesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CategoryPage;
import pages.CoursePage;
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
    private final String menuCourseSelectorTemplate = "//div[contains(., '%s') and contains(@class, 'lessons__new-item-title')]";
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

    public List<WebElement> getCoursesFromMainPage() {
        List<WebElement> listCourses = driver.findElements(By.cssSelector("a.lessons__new-item"));
        List<WebElement> listTitleCourses = driver.findElements((By.cssSelector("div.lessons__new-item-title")));
        List<String> titleCourses = null;
        String title = null;
        for(WebElement element : listTitleCourses) {
            title = element.getText();
            titleCourses.add(title);
        }
        return listCourses;
    }


    public CoursePage clickCourse(CoursesData coursesData) {
        String selector = String.format(menuCourseSelectorTemplate, coursesData.getName());
        driver.findElement(By.xpath(selector)).click();
        return new CoursePage(driver);
    }

    public MenuComponent checkTitlePage(CoursePage coursePage, CoursesData coursesData) {
        String title = coursePage.getPageTitle();
        assertThat("Тайтл не совпал", title, equalTo(coursesData.getName()));
        return this;
    }

}
