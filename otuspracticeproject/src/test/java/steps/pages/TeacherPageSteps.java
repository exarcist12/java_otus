package steps.pages;

import com.google.inject.Inject;
import pages.TeacherPage;
import io.cucumber.java.ru.Тогда;

public class TeacherPageSteps {

  @Inject
  public TeacherPage teacherPage;

  @Тогда("Откроется страница карточки преподавателя")
  public void teacherPageShouldBeOpened() {
    teacherPage.pageHeaderShouldBeSameAs("Преподаватели");
  }

}