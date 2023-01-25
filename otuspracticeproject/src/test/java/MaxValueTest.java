import annotations.Driver;
import components.MenuComponent;
import data.Course;
import data.CoursesData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import data.pages.CoursePage;
import data.pages.MainPage;
import support.GuiceScoped;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.List;
import java.util.function.BinaryOperator;

@ExtendWith(UIExtension.class)
public class MaxValueTest {

  @Driver
  private WebDriver driver;
  @Inject
  GuiceScoped guiceScoped;

  @Test
  public void maxValueTest() throws PathEmptyException, ParseException {
    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
    MenuComponent menuComponent = new MenuComponent(driver);
    List<Course> coursesWithDate = menuComponent.coursesWithDate();
    CoursesData coursesData = menuComponent.function(coursesWithDate, BinaryOperator.maxBy((p1,p2) -> p1.compareTo(p2))).getCoursesData();
    CoursePage coursePage = menuComponent.clickCourse(coursesData);
    menuComponent.checkTitlePage(coursePage, CoursesData.PHPDEVELOPER);
  }
}
