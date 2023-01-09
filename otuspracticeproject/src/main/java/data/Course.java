package data;

import java.util.Date;

public class Course {
    Date date;
    CoursesData coursesData;

    public Course(){
        this.date = date;
        this.coursesData = coursesData;
    }

    public Date getDate() {
        return date;
    }

    public CoursesData getCoursesData() {
        return coursesData;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCoursesData(CoursesData coursesData) {
        this.coursesData = coursesData;
    }
}
