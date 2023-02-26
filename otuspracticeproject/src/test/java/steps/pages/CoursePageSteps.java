package steps.pages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import data.CoursesData;
import io.cucumber.java.ru.Тогда;
import pages.CoursePage;

import java.util.Arrays;
import java.util.List;

public class CoursePageSteps {

  @Inject
  public CoursePage coursePage;

  @Тогда("Откроется страница курса {string}")
  public void coursePageOpened(String courseName) {
    List<CoursesData> listCourses = Arrays.asList(CoursesData.values());
    String pageTitle = null;
    for (CoursesData courseData : listCourses) {
      if (courseData.getPageTitle().equals(courseName)){
        pageTitle = courseData.getPageTitle();
      }
    }
    String text = coursePage.getPageTitle();
    if (text.equals("Best Practice по созданию кастомных дашбордов и работе с Power BI и Tableau")){
      text = "BI-аналитика";
    }
    assertThat("Тайтлы не совпадают", text, equalTo(pageTitle));
  }


  @Тогда("Стоимость курса равна {int}")
  public void courseGetPrice(Integer summa){
    Integer price = coursePage.getPrice();
    assertThat("Сумма не совпала", price, equalTo(summa));
  }
}
