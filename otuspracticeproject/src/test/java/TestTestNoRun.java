import annotations.Driver;
import components.MenuComponent;
import data.CoursesData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import listeners.MouseListener;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.CoursePage;
import pages.MainPage;

import java.util.Arrays;
import java.util.List;

@ExtendWith(UIExtension.class)
public class TestTestNoRun {

    @Driver
    private WebDriver driver;

    @Test
    public void clickCourseMenuTest() throws PathEmptyException, InterruptedException {

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new MouseListener());
        MainPage mainPage = new MainPage(eventFiringWebDriver);
        mainPage.open();
        eventFiringWebDriver.manage().window().fullscreen();
        MenuComponent menuComponent = new MenuComponent(eventFiringWebDriver);
        CoursePage coursePage = menuComponent.clickCourse(CoursesData.JAVASPECIALIZATION);
        menuComponent.checkTitlePage(coursePage, CoursesData.JAVASPECIALIZATION);

    }

    @Test
    public void checkBorder2() throws PathEmptyException {

        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        driver.manage().window().fullscreen();
        MenuComponent menuComponent = new MenuComponent(driver);
        menuComponent.getCoursesDataFromMainPage(menuComponent.getCoursesStringFromMainPage());

    }

    @Test
    public void test() throws PathEmptyException {
        String name = "Team Lead";
        CoursesData coursesData = null;
        List<CoursesData>  list = Arrays.asList(CoursesData.values());
        for (int i = 0; i< list.size(); i++){
            if (list.get(i).getName() == name){
                coursesData = list.get(i);
            }
        }
    }

}
