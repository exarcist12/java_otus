import annotations.Driver;
import components.MenuComponent;
import data.CategoryData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import support.GuiceScoped;

import javax.inject.Inject;

@ExtendWith(UIExtension.class)
public class ClickCategoryMenuTest {

  @Driver
  private WebDriver driver;
  @Inject
  GuiceScoped guiceScoped;
  @Test
  public void clickCategoryMenuItem() throws PathEmptyException, InterruptedException {

    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
    MenuComponent menuComponent = new MenuComponent(guiceScoped);
    menuComponent.clickCategory(CategoryData.PROGRAMMER);
    menuComponent.menuItemActive(CategoryData.PROGRAMMER);

  }
}
