import annotations.Driver;
import components.MenuComponent;
import data.CoursesData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.MainPage;

import java.util.List;

@ExtendWith(UIExtension.class)
public class ClickCourseMenuTest {

  @Driver
  private WebDriver driver;

  @Test
  public void clickCourseMenuTest() throws PathEmptyException {

    String courseName = "Apache Kafka";
    MainPage mainPage = new MainPage(driver);
    mainPage.open();
    driver.manage().window().fullscreen();
    MenuComponent menuComponent = new MenuComponent(driver);
    CoursesData coursesData = menuComponent.filterCourseName(courseName);
    CoursePage coursePage = menuComponent.clickCourse(coursesData);
    menuComponent.checkTitlePage(coursePage, coursesData);
  }
}
