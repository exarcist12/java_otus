package data.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

public class TeacherPage extends AbsBasePage<TeacherPage> {

	@Inject
	public TeacherPage(GuiceScoped guiceScoped) {
		super(guiceScoped);
	}

	@FindBy(xpath = "//div[text()='Преподаватели']")
	private WebElement header;

	public TeacherPage pageHeaderShouldBeSameAs(String header) {
		assert this.header.getText().equals(header): "Header is invalid";

		return this;
	}
}
