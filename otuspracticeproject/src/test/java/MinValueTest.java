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

@ExtendWith(UIExtension.class)
public class MinValueTest {

   @Driver
   private WebDriver driver;

   @Test
   public void test() throws PathEmptyException, ParseException {
      MainPage mainPage = new MainPage(driver);
      mainPage.open();
      MenuComponent menuComponent = new MenuComponent(driver);
      List<Course> coursesWithDate = menuComponent.coursesWithDate();
      CoursesData coursesData = menuComponent.minCourse(coursesWithDate).getCoursesData();
      CoursePage coursePage = menuComponent.clickCourse(coursesData);
      menuComponent.checkTitlePage(coursePage, CoursesData.LINUXADMINISTRATOR);
   }
}
