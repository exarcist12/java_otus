package pages;

import org.openqa.selenium.WebDriver;

public class CoursePage extends AbsBasePage<CoursePage> {
    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
