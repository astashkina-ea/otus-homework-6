package factories;

import exception.BrowserNorSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

public interface IFactory {
    WebDriver createDriver() throws BrowserNorSupportedException;
    WebDriverManager setUp() throws BrowserNorSupportedException;
}