package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.GuiceScoped;

import javax.inject.Inject;
import java.util.List;

public class CoursePage extends AbsBasePage<CoursePage> {

  private final String titleTemplate = "h1";
  @Inject
  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @FindBy(css = "h1")
  private List<WebElement> titleElement;
  public String getPageTitle() {
    String text = titleElement.get(0).getAttribute("innerText");
    return text;
  }
}
