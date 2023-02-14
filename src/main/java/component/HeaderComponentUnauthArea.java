package component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponentUnauthArea extends AbsBaseComponent{

    public HeaderComponentUnauthArea(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header3__button-sign-in")
    private WebElement signInButton;

    public void clickSignInButton() {
        signInButton.click();
    }
}