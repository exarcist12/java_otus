package pages;

import annotations.Path;
import com.google.inject.Inject;
import exceptions.PathEmptyException;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;
import support.GuiceScoped;
import waiters.StandartWaiter;

public abstract class AbsBasePage<T> extends AbsPageObject<T> {

  protected WebDriver driver;
  protected StandartWaiter standartWaiter;
  @Inject
  protected GuiceScoped guiceScoped;

  private String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");

  //  public AbsBasePage(WebDriver driver){
  //    this.driver = driver;
  //  }


  public AbsBasePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private String getPagePath() {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if(clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getAnnotation(Path.class);
      return path.value();
    }

    return "";
  }


  private String getPath() throws PathEmptyException{
    Class clazz = this.getClass();

    if (clazz.isAnnotationPresent(Path.class)){
      Path path = (Path)clazz.getAnnotation(Path.class);
      return path.value();

    }

    throw new PathEmptyException();
  }

  public T open() {

    guiceScoped.driver.get(baseUrl + getPagePath());

    return (T) this;
  }
}
