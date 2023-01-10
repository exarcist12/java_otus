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
import java.util.stream.Collectors;

@ExtendWith(UIExtension.class)
public class MaxValueTest {

   @Driver
   private WebDriver driver;

   @Test
   public void test() throws PathEmptyException, ParseException {
      MainPage mainPage = new MainPage(driver);
      mainPage.open();
      MenuComponent menuComponent = new MenuComponent(driver);
      List<Course> coursesWithDate = menuComponent.coursesWithDate();
      CoursesData coursesData = menuComponent.maxCourse(coursesWithDate).getCoursesData();
      CoursePage coursePage = menuComponent.clickCourse(coursesData);
      menuComponent.checkTitlePage(coursePage, CoursesData.PHPDEVELOPER);
   }
}
