import annotations.Driver;
import components.MenuComponent;
import data.Course;
import data.CoursesData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.MainPage;

import java.text.ParseException;
import java.util.List;
import java.util.function.BinaryOperator;

@ExtendWith(UIExtension.class)
public class MinValueTest {

  @Driver
  private WebDriver driver;

  @Test
  public void minValueTest() throws PathEmptyException, ParseException {
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    MenuComponent menuComponent = new MenuComponent(driver);
    List<Course> coursesWithDate = menuComponent.coursesWithDate();
    CoursesData coursesData = menuComponent.function(coursesWithDate, BinaryOperator.minBy((p1, p2) -> p1.compareTo(p2))).getCoursesData();
    CoursePage coursePage = menuComponent.clickCourse(coursesData);
    menuComponent.checkTitlePage(coursePage, CoursesData.MACHINELEARNING);
  }
}
