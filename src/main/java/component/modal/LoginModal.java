package component.modal;

import component.AbsBaseComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginModal extends AbsBaseComponent implements ILoginModal {

    public LoginModal(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "form.new-log-reg__form")
    private WebElement loginModal;

    @FindBy(css = "form.new-log-reg__form [name='email']")
    private WebElement emailField;

    @FindBy(css = "form.new-log-reg__form [name='password']")
    private WebElement passwordField;

    @FindBy(css = "form.new-log-reg__form [type='submit']")
    private WebElement signInButton;

    public void fillAuthForm() {
        webDriverWait.waitForVisibleElement(emailField);
        enterToTextArea(emailField, System.getProperty("login"));
        enterToTextArea(passwordField, System.getProperty("password"));
        signInButton.click();
    }

    @Override
    public void modalShouldNotBeVisible() {
        webDriverWait.waitForInvisibleElement((loginModal));
    }

    @Override
    public void modalShouldBeVisible() {
        webDriverWait.waitForVisibleElement(loginModal);
    }
}