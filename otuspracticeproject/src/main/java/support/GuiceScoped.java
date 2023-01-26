package support;

import com.google.inject.Guice;
import driver.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {
	public WebDriver driver = new WebDriverFactory().getDriver();

}
