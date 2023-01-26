package steps.courseBlock;

import com.google.inject.Inject;
import components.CoursesComponent;
import components.MenuComponent;
import components.TeacherBlock;
import data.CoursesData;
import io.cucumber.java.ru.Если;

public class CourseBlocksSteps {

	@Inject
	private CoursesComponent coursesComponent;


	@Если("Кликнуть на курс {string}")
	public void clickCourse(String courseName) {

		coursesComponent.clickCoursePage(courseName);
	}

}
