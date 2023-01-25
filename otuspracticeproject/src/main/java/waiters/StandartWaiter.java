package waiters;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class StandartWaiter {


  private WebDriver driver;

  public StandartWaiter(WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForCondition(ExpectedCondition condition) {
    WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
    try {
      webDriverWait.until(condition);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }
}