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
import support.GuiceScoped;

import javax.inject.Inject;

@ExtendWith(UIExtension.class)
public class ClickCourseMenuTest {

  @Driver
  private WebDriver driver;
  @Inject
  GuiceScoped guiceScoped;

  @Test
  public void clickCourseMenuTest() throws PathEmptyException {

    String courseName = "Специализация сетевой инженер";
    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
    driver.manage().window().fullscreen();
    MenuComponent menuComponent = new MenuComponent(guiceScoped);
    CoursesData coursesData = menuComponent.filterCourseName(courseName);
    CoursePage coursePage = menuComponent.clickCourse(coursesData);
    menuComponent.checkTitlePage(coursePage, coursesData);
  }
}
