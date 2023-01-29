package components;

import com.google.inject.Inject;
import data.Course;
import data.CoursesData;
import io.cucumber.java.mn.Харин;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CoursePage;
import support.GuiceScoped;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.not;

public class CoursesComponent extends AbsComponent<CoursesComponent> {

  private String baseUrl = System.getProperty("webdriver.base.url", "https://otus.ru");
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



  private final String dateStartTemplate = "div.lessons__new-item-start";
  private final String timeTemplate = "div.lessons__new-item-time";
  private final String titleCourseTemplate = "div.lessons__new-item-title";
  @FindBy(css = "div.lessons__new-item-container")
  List<WebElement> elementsCourse;

  public ArrayList<Course>  findCourses() throws ParseException {

    ArrayList<Course> coursesListWithDate = new ArrayList<>();
    Course courseWithDate = new Course();
    CoursesData courseDataData;
    List<CoursesData> coursesDataList = Arrays.asList(CoursesData.values());

    for (WebElement elementCourse: elementsCourse){
      WebElement elementName = elementCourse.findElement(By.cssSelector(titleCourseTemplate));
      String nameCourse = elementName.getText();

      courseDataData = coursesDataList.stream().filter(p -> (p.getName().equals(nameCourse))).findFirst().get();

      List<WebElement> elementsDate = elementCourse.findElements(By.cssSelector(dateStartTemplate));
      if (elementsDate.size()==0){
        elementsDate = elementCourse.findElements(By.cssSelector(timeTemplate));
      }

      String dateString = elementsDate.get(0).getAttribute("innerText");

      LocalDate date = getDate(getDateString(dateString));

      courseWithDate.setCoursesData(courseDataData);
      courseWithDate.setDate(date);
      coursesListWithDate.add(courseWithDate);
      courseWithDate = new Course();
    }

    return coursesListWithDate;
  }

  public void coursesWithDate(LocalDate dateCompare) throws ParseException {

    ArrayList<Course> coursesListWithDate = findCourses();

    for (Course course : coursesListWithDate){
      if ((dateCompare.equals(course.getDate()))||(dateCompare.isBefore(course.getDate()))){
        System.out.println("Название курса: " + course.getCoursesData().getName());
        System.out.println("Дата курса: " + course.getDate());
      }
    }

  }

  public void courseWithPrice(String value) throws ParseException, IOException {
    ArrayList<Course> coursesListWithDate = findCourses();
    String price;
    Document doc = Jsoup.connect(baseUrl)
            .get();
    Course course;
    Elements listBlocks = doc.select("a.lessons__new-item");
    for (Element blok : listBlocks) {

      String href = blok.attr("href");
      Elements block = blok.select("div.lessons__new-item-title");
      String nameCourse = block.get(0).text();
      course = coursesListWithDate.stream().filter(p -> p.getCoursesData().getName().equals(nameCourse)).findFirst().get();
      Document documentCourse = null;
      if (!href.equals("/promo/php-specialization/")) {
        documentCourse = Jsoup.connect(baseUrl + href)
                .get();



      Element x = null;
      Elements documentCourseElements = documentCourse.select("div.tn-atom");
      for (Element element : documentCourseElements) {
        if (element.text().contains("Стоимость обучения")) {
          Elements children;
          List<Element> newlistchild = new ArrayList<>();
          List<Element> newlistchildWithoutEmptyFields = new ArrayList<>();
          x = element;
          Element parent = x.parent();
          Element parentPrice = parent.parent();
          Elements childsParentPrice = parentPrice.children();
          String s;
          Integer a = 10;
          children = parentPrice.getAllElements();
          for (Element child : children) {
            Elements spisok = child.getAllElements();
            if (child.getAllElements().size() == 1) {
              newlistchild.add(child);

            }
          }

          for (Element elementNewListChild : newlistchild) {
            if (!elementNewListChild.text().equals("")) {
              newlistchildWithoutEmptyFields.add(elementNewListChild);
            }
          }

          for (int i = 0; i < newlistchildWithoutEmptyFields.size(); i++) {
            if (newlistchildWithoutEmptyFields.get(i).text().contains("₽")) {
              String text = newlistchildWithoutEmptyFields.get(i).text();
              price = text.replaceAll("[^\\d]", "");
              Integer sum = Integer.valueOf(price);
              course.setPrice(sum);
            }
          }
        }
      }

      if (x == null) {
        Elements children;
        List<Element> newlistchild = new ArrayList<>();
        List<Element> newlistchildWithoutEmptyFields = new ArrayList<>();
        documentCourseElements = documentCourse.select("div#payment-link");
        if (documentCourseElements.size() != 0) {
          children = documentCourseElements.get(0).getAllElements();
          for (Element child : children) {
            Elements spisok = child.getAllElements();
            if (child.getAllElements().size() == 1) {
              newlistchild.add(child);

            }
          }

          for (Element elementNewListChild : newlistchild) {
            if (!elementNewListChild.text().equals("")) {
              newlistchildWithoutEmptyFields.add(elementNewListChild);
            }
          }

          for (int i = 0; i < newlistchildWithoutEmptyFields.size(); i++) {
            if (newlistchildWithoutEmptyFields.get(i).text().contains("₽")) {
              String text = newlistchildWithoutEmptyFields.get(i).text();
              price = text.replaceAll("[^\\d]", "");
              Integer sum = Integer.valueOf(price);
              course.setPrice(sum);
            }
          }
        }

        if (documentCourseElements.size() == 0) {
          documentCourseElements = documentCourse.select("div.course-bottom-bar-meta__value nobr");
          String text = documentCourseElements.text();
          price = text.replaceAll("[^\\d]", "");
          Integer sum = Integer.valueOf(price);
          course.setPrice(sum);
        }

      }

    }
      if (href.equals("/promo/php-specialization/")) {
        if (value.equals("максимальной")) {
          course.setPrice(0);
        } else if(value.equals("минимальной")){
          course.setPrice(Integer.MAX_VALUE);
        }
      }
    }


    List<Integer> prices = coursesListWithDate.stream().map(p1 -> p1.getPrice()).collect(Collectors.toList());
    Integer priceC = 0;
    if (value.equals("максимальной")) {
      priceC = prices.stream().reduce(BinaryOperator.maxBy((p1,p2) -> p1.compareTo(p2))).get();
    } else if(value.equals("минимальной")){
      priceC = prices.stream().reduce(BinaryOperator.minBy((p1,p2) -> p1.compareTo(p2))).get();
    }

    Integer finalPriceC = priceC;
    Course courseWithPrice = coursesListWithDate.stream().filter(p1 -> p1.getPrice() == finalPriceC).findFirst().get();

    WebElement webElementCourse = null;
    for (WebElement element : courseTabs) {
      webElementCourse = element.findElement(By.xpath("//div[contains(text(), '"+ courseWithPrice.getCoursesData().getName() +"')]"));
    }

    if (buttonCoockies.size()>0){
      buttonCoockies.get(0).click();
    }
    webElementCourse.click();

  }
}
