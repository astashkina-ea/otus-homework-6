package data;

public enum DropDownMenuData {

    PERSONAL("personal");

    private final String name;

    DropDownMenuData(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
