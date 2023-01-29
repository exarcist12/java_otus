package data;

import java.time.LocalDate;
import java.util.Date;

public class Course {
  LocalDate date;
  CoursesData coursesData;

  Integer price;

  public Course(){
    this.date = date;
    this.coursesData = coursesData;
    this.price = price;
  }

  public LocalDate getDate() {
    return date;
  }

  public Integer getPrice() {
    return price;
  }

  public CoursesData getCoursesData() {
    return coursesData;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }

  public void setCoursesData(CoursesData coursesData) {
    this.coursesData = coursesData;
  }
}
