package steps.pages;

import com.google.inject.Inject;
import data.CoursesData;
import io.cucumber.java.ru.Тогда;
import pages.CoursePage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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
		assertThat("Тайтлы совпадают", text, equalTo(pageTitle));
	}

}
