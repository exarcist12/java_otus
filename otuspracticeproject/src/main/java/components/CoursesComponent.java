package components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;
import pages.TeacherPage;
import support.GuiceScoped;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class CoursesComponent extends AbsComponent<CoursesComponent> {
  @Inject
  public CoursesComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }


  public String getDateString(String dateString){
    List<String> listString= new ArrayList<>(Arrays.asList(dateString.split(" ")));
    if (listString.get(0).equals("")){
      listString.remove(0);
    }

    if (listString.get(0).equals("С")){
      listString.remove(0);
    }

    if (listString.size()>2){
      int i = 0;
      while ((i<5) && (listString.size()>3)) {
        i++;
        listString.remove(3);
      }

      if (listString.get(0).equals("В")){
        listString.set(0, "1");
      }

      for(int j = 3; j<listString.size(); j++){
        listString.remove(j);
      }

      if (Integer.valueOf(listString.get(2)) < 2022) {
        listString.remove(2);
        listString.add("2023");
      }

      if (listString.get(1).equals("октябре")){
        listString.set(1, "октября");
      }
    }

    if ((listString.size()<3)) {
      listString.add("2023");
    }

    String date = String.join(" ", listString);
    return date;
  }

  public LocalDate getDate(String dateString) throws ParseException {
    Date date;
    LocalDate localDate;
    try {
      DateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
      date = format.parse(dateString);
      localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    }
    catch(ParseException e) {
      DateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("en"));
      date = format.parse(dateString);
      localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    return localDate;
  }



  @FindBy(css = ".lessons__new-item-container")
  private List<WebElement> courseTabs;

  @FindBy(css = "button.cookies__button")
  private List<WebElement> buttonCoockies;


  public CoursePage clickCoursePage(String text) {
    WebElement course = null;
    for (WebElement element : courseTabs) {
      course = element.findElement(By.xpath("//div[contains(text(), '"+ text +"')]"));
    }

    if (buttonCoockies.size()>0){
      buttonCoockies.get(0).click();
    }
    course.click();
    return new CoursePage(guiceScoped);

  }
}
