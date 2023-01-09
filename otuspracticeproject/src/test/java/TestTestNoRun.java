import annotations.Driver;
import components.CoursesComponent;
import components.MenuComponent;
import data.Course;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BinaryOperator;

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
    public void test() throws PathEmptyException, ParseException {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        MenuComponent menuComponent = new MenuComponent(driver);
        List<Course> coursesWithDate = menuComponent.coursesWithDate();
        Integer time  = (int) coursesWithDate.get(0).getDate().getTime();
        List<Date> course = menuComponent.times(coursesWithDate);

    }

    @Test
    public void checkBorder3() throws ParseException {
        CoursesComponent coursesComponent = new CoursesComponent(driver);
//        String date = coursesComponent.getDateString("28 декабря 2022 года 15 месяцев");
        Date date = coursesComponent.getDate(coursesComponent.getDateString("28 декабря 2022 года 15 месяцев"));
    }

    @Test
    public void test2(){
        ArrayList<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(22L);
        list.add(3L);
        list.add(33L);
        list.add(5454L);
        list.add(6L);
        Long count = list.stream().reduce(BinaryOperator.maxBy((p1, p2) -> (int) (p2 - p1))).get();
    }
}
