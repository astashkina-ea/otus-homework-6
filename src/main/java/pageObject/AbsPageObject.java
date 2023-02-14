package pageObject;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Waiters webDriverWait;
    protected Actions actions;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new Waiters(driver);
        this.actions = new Actions(driver);

        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By selector) {
        return driver.findElement(selector);
    }

    public void enterToTextArea(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    public void moveToElement(WebElement webElement) {
        webDriverWait.waitForVisibleElement(webElement);
        actions.moveToElement(webElement).perform();
    }

    public void moveToElementAndClick(WebElement webElement) {
        actions.moveToElement(webElement).click().perform();
    }

    public void checkAttribute(WebElement webElement, String expect) {
        Assertions.assertEquals(expect, webElement.getAttribute("value"));
    }

    public void checkText(WebElement webElement, String expect) {
        Assertions.assertEquals(expect, webElement.getText());
    }

    public void checkAttributeChecked(WebElement webElement) {
        Assertions.assertTrue(webElement.isSelected());
    }
}