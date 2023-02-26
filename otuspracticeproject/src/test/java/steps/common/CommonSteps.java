package steps.common;

import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pages.MainPage;
import io.cucumber.java.ru.Пусть;

import java.io.IOException;

public class CommonSteps {

  @Inject
  private MainPage mainPage;

  @Пусть("Открыта главная страница в браузере")
  public void openPage() {
    mainPage.open();
  }
}
