package components;

import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CoursesComponent extends AbsBaseComponent<CoursesComponent> {

   public CoursesComponent(WebDriver driver) {
      super(driver);
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

   public Date getDate(String dateString) throws ParseException {
      Date date;
      try {
         DateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
         date = format.parse(dateString);
      }
      catch(ParseException e) {
         DateFormat format = new SimpleDateFormat("dd MMMM yyyy", new Locale("en"));
         date = format.parse(dateString);
      }

      return date;
   }
 }
