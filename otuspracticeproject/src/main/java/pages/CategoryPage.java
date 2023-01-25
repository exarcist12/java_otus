package pages;

import com.google.inject.Inject;
import support.GuiceScoped;

public class CategoryPage extends AbsBasePage<CategoryPage> {
  @Inject
  public CategoryPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
