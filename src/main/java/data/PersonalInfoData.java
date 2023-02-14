package data;

public enum PersonalInfoData {

    FNAME("fname"),
    LNAME("lname"),
    FNAME_LATIN("fname_latin"),
    LNAME_LATIN("lname_latin"),
    BLOG_NAME("blog_name");

    private String name;

    PersonalInfoData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}