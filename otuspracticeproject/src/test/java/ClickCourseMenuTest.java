import annotations.Driver;
import components.MenuComponent;
import data.CategoryData;
import data.CoursesData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class ClickCourseMenuTest {

    @Driver
    private WebDriver driver;
    @Test
    public void clickCourseMenuTest() throws PathEmptyException, InterruptedException {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        MenuComponent menuComponent = new MenuComponent(driver);
        CoursePage coursePage = menuComponent.clickCourse(CoursesData.JAVASPECIALIZATION);
        menuComponent.checkTitlePage(coursePage, CoursesData.JAVASPECIALIZATION);



    }
}
