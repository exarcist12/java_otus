package data;

public enum CoursesData {
    JAVASPECIALIZATION("Специализация Java-разработчик", CategoryData.PROGRAMMER);

    private String name;
    private CategoryData categoryData;

    CoursesData (String name, CategoryData categoryData){
        this.name = name;
        this.categoryData = categoryData;
    }

    public String getName() {
        return name;
    }

    public CategoryData getCategoryData() {
        return categoryData;
    }
}