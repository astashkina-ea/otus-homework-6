package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainBasePage extends AbsBasePage {

    private By signInButton = By.cssSelector(".header3__button-sign-in");

    public MainBasePage(WebDriver driver) {
        super(driver, "/");
    }
}