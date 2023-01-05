import annotations.Driver;
import components.MenuComponent;
import data.CategoryData;
import exceptions.PathEmptyException;
import extensions.UIExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

@ExtendWith(UIExtension.class)
public class ClickCategoryMenuTest {

    @Driver
    private WebDriver driver;
    @Test
    public void clickCategoryMenuItem() throws PathEmptyException, InterruptedException {

        new MainPage(driver).open();
        driver.manage().window().fullscreen();
        MenuComponent menuComponent = new MenuComponent(driver);
        menuComponent.clickCategory(CategoryData.PROGRAMMER);
        Thread.sleep(5000);
        menuComponent.menuItemActive(CategoryData.PROGRAMMER);



    }
}
