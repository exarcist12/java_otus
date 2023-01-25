package data.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import support.GuiceScoped;

import javax.inject.Inject;

public class CoursePage extends AbsBasePage<CoursePage> {

  private final String titleTemplate = "h1";
  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  public String getPageTitle() {
    return driver.findElement(By.cssSelector(titleTemplate)).getAttribute("innerText");
  }
}
