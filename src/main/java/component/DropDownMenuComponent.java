package component;

import data.DropDownMenuData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropDownMenuComponent extends AbsBaseComponent{

    public DropDownMenuComponent(WebDriver driver) {
        super(driver);
    }

    private String userItemProfile = "a[href*=%s]";

    public void clickUserItem() {
        String locator = String.format(userItemProfile, DropDownMenuData.PERSONAL.getName());
        $(By.cssSelector(locator)).click();
    }
}
