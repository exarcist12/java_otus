package components;

import data.CategoryData;
import data.CoursesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.CategoryPage;
import pages.CoursePage;
import pages.MainPage;
import waiters.StandartWaiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private final String logo = ".header3__logo-img";
    private final String buttonCoockiesTemplate = "button.cookies__button";
    private final String titlesTemplate = "div.lessons__new-item-title";

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

    public List<String> getCoursesStringFromMainPage() {

        List<WebElement> listTitleCourses = driver.findElements((By.cssSelector(titlesTemplate)));
        ArrayList<String> titleCourses = new ArrayList<>();
        String title;
        for(WebElement element : listTitleCourses) {
            title = element.getText();
            titleCourses.add(title);
        }

        return titleCourses;
    }

    public List<CoursesData> getCoursesDataFromMainPage(List<String> list) {

        List<CoursesData>  listEnums = Arrays.asList(CoursesData.values());

        List<CoursesData> newList = listEnums.stream().filter(s ->
                list.contains(s.getName())
        ).collect(Collectors.toList());

        return newList;
    }

    public CoursesData filterCourseName(String name) {

        List<String> courseString =  getCoursesStringFromMainPage();
        List<CoursesData> coursesData = getCoursesDataFromMainPage(courseString);
        List<CoursesData> coursesData2 = coursesData.stream().filter(s -> s.getName() == name).collect(Collectors.toList());

        return coursesData2.get(0);
    }


    public CoursePage clickCourse(CoursesData coursesData) {

        String selector = String.format(menuCourseSelectorTemplate, coursesData.getName());
        WebElement element = driver.findElement(By.xpath(selector));
        List<WebElement> buttonCoockies = driver.findElements(By.cssSelector(buttonCoockiesTemplate));
        if (buttonCoockies.size()>0){
            buttonCoockies.get(0).click();
        }
        new Actions(driver).moveToElement(element).click().perform();

        return new CoursePage(driver);
    }

    public CoursesComponent checkTitlePage(CoursePage coursePage, CoursesData coursesData) {

        String title = coursePage.getPageTitle();
        assertThat("Тайтл не совпал", title, equalTo(coursesData.getNameOnPage()));

        return new CoursesComponent(driver);
    }


    public MainPage clickMainPage() {

        String selector = String.format(logo);
        driver.findElement(By.cssSelector(selector)).click();

        return new MainPage(driver);
    }

}
