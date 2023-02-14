package factories;

import data.BrowserData;
import exception.BrowserNorSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory implements IFactory{

    private String browserName = System.getProperty("browser.name", "chrome");

    @Override
    public WebDriver createDriver() throws BrowserNorSupportedException {

        switch (BrowserData.valueOf(browserName.toUpperCase())) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-fullscreen");
                return new ChromeDriver();
            default:
                throw new BrowserNorSupportedException(browserName);
        }
    }

    @Override
    public WebDriverManager setUp() throws BrowserNorSupportedException {
        switch (BrowserData.valueOf(browserName.toUpperCase())) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return null;
            default:
                throw new BrowserNorSupportedException(browserName);
        }
    }
}