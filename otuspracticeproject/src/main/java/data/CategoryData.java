package data;

public enum CategoryData {

    PROGRAMMER("Программирование");

    private String name;
    CategoryData(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
