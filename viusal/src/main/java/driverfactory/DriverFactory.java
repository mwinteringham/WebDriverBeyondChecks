package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by richard on 17/07/2016.
 */
public class DriverFactory
{
    public WebDriver create()
    {
        String pathToChromeDriver = System.getProperty("user.dir") + "/src/main/java/driverfactory/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        ChromeDriver driver = new ChromeDriver();

        return driver;
    }
}
