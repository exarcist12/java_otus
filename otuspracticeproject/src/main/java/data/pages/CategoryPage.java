package data.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import support.GuiceScoped;

public class CategoryPage extends AbsBasePage<CategoryPage> {
  @Inject
  public CategoryPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
