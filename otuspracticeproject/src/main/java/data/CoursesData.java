package data;

public enum CoursesData {
    JAVASPECIALIZATION("Специализация Java-разработчик", "Специализация Java-разработчик",CategoryData.PROGRAMMER),
    TEAMLEAD("Team Lead", "Team Lead", CategoryData.PROGRAMMER),
    LINUXPROFESSIONAL("Administrator Linux. Professional", "Administrator Linux. Professional", CategoryData.PROGRAMMER),
    SYSTEMANALYSTADVANCED("Системный аналитик. Advanced", "Системный аналитик. Advanced", CategoryData.PROGRAMMER),
    QAAUTOJAVA("Специализация QA Automation Engineer", "QA AutomationEngineer", CategoryData.PROGRAMMER),
    FULLSTACKDEV("Специализация Fullstack Developer", "FullstackDeveloper", CategoryData.PROGRAMMER),
    LINUXADMINISTRATOR("Специализация Administrator Linux", "Administrator Linux", CategoryData.PROGRAMMER),
    NETWORKENGINEER("Специализация сетевой инженер", "Network Engineer", CategoryData.PROGRAMMER),
    MACHINELEARNING("Специализация Machine Learning", "Machine Learning", CategoryData.PROGRAMMER),
    SYSTEMANALYST("Специализация Системный аналитик", "Системный аналитик", CategoryData.PROGRAMMER),
    PYTHONDEVELOPER("Специализация Python", "Python Developer", CategoryData.PROGRAMMER),
    IOSDEVELOPER("Специализация iOS Developer", "iOS Developer", CategoryData.PROGRAMMER),
    ANDROIDDEVELOPER("Специализация Android", "Android Developer", CategoryData.PROGRAMMER),
    CPLUSPLUS("Специализация С++", "C++ DEVELOPER", CategoryData.PROGRAMMER),
    CSHARP("Специализация С#", "C# Developer", CategoryData.PROGRAMMER),
    PHPDEVELOPER("Специализация PHP Developer", "СпециализацияPHP Developer", CategoryData.PROGRAMMER),
    PHPDEVELOPER2("Специализация PHP Developer2", "Специализация PHP Developer2", CategoryData.PROGRAMMER),
    PHPDEVELOPER3("Специализация PHP Develope3","Специализация PHP Developer3", CategoryData.PROGRAMMER),
    PHPDEVELOPER4("Специализация PHP Developer4", "Специализация PHP Developer4", CategoryData.PROGRAMMER),
    PHPDEVELOPER5("Специализация PHP Developer5", "Специализация PHP Developer5", CategoryData.PROGRAMMER),
    PHPDEVELOPER6("Специализация PHP Developer6", "Специализация PHP Developer6", CategoryData.PROGRAMMER),
    PHPDEVELOPER7("Специализация PHP Developer7", "Специализация PHP Developer7", CategoryData.PROGRAMMER);

    private String name;
    private String nameOnPage;
    private CategoryData categoryData;

    CoursesData (String name, String nameOnPage, CategoryData categoryData){
        this.name = name;
        this.nameOnPage = nameOnPage;
        this.categoryData = categoryData;
    }

    public String getName() {
        return name;
    }

    public String getNameOnPage() {
        return nameOnPage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CategoryData getCategoryData() {
        return categoryData;
    }


}