package pages;

import annotations.Path;
import com.google.inject.Inject;
import support.GuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }
}
