package steps.courseBlock;

import com.google.inject.Inject;
import components.CoursesComponent;
import io.cucumber.java.ru.Если;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.function.BinaryOperator;

public class CourseBlocksSteps {

	@Inject
	private CoursesComponent coursesComponent;

	@Если("Кликнуть на курс {string}")
	public void clickCourse(String courseName) {
		coursesComponent.clickCoursePage(courseName);
	}


	@Если("Введена дата {string}, то отобразить эти курсы в консоли")
	public void showCourses(String date) throws ParseException {
		coursesComponent.coursesWithDate(LocalDate.parse(date));
	}

	@Если("Кликнуть по курсу с {string} ценой")
	public void checkPrice(String value) throws ParseException, IOException {
		coursesComponent.courseWithPrice(value);
	}


}
