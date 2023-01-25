package driver;

import com.google.inject.Inject;
import data.BrowserData;
import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Locale;

import static data.BrowserData.CHROME;
import static data.BrowserData.OPERA;

public class WebDriverFactory implements IDriverFactory {

	private String browserName = "";

	@Inject
	public WebDriverFactory() {
		browserName = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);
	}

	public WebDriver getDriver() {
		boolean isSupported = false;

		for(BrowserData browserData: BrowserData.values()) {
			if(browserName.equals(browserData.name().toLowerCase(Locale.ROOT))) {
				isSupported = true;
			}
		}

		if(!isSupported) {
			try {
				throw new DriverTypeNotSupported(browserName);
			} catch(DriverTypeNotSupported ignored) {

			}
		}

		switch(BrowserData.valueOf(browserName.toUpperCase(Locale.ROOT))) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				return new ChromeDriver();
			case OPERA:
				WebDriverManager.operadriver().setup();
				return new OperaDriver();
			default:
				try {
					throw new DriverTypeNotSupported(browserName);
				} catch (DriverTypeNotSupported ignored) {

				}
		}

		return null;
	}


}
