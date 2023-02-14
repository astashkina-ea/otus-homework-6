package data;

public enum MessengersData {

    SKYPE("skype"),
    TELEGRAM("telegram");

    private String name;

    MessengersData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}