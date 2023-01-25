package steps.teacherblock;

import com.google.inject.Inject;
import components.TeacherBlock;
import io.cucumber.java.ru.Если;

public class TeacherBlockSteps {

	@Inject
	private TeacherBlock teacherBlock;

	@Если("Кликнуть на {int} плитку преподавателя")
	public void clickTeacherTile(int number) {
		teacherBlock.clickTeacherTileByIndex(number);
	}

}