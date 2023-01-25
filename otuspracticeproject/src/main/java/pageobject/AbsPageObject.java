package pageobject;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import support.GuiceScoped;
import waiters.StandartWaiter;

public abstract class AbsPageObject<T> {

	protected WebDriver driver;
	protected StandartWaiter standartWaiter;
	protected GuiceScoped guiceScoped;

	@Inject
	public AbsPageObject(GuiceScoped guiceScoped) {
		this.guiceScoped = guiceScoped;
		this.driver = guiceScoped.driver;
		this.standartWaiter = new StandartWaiter(driver);

		PageFactory.initElements(guiceScoped.driver, this);
	}

}