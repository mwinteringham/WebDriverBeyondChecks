package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by richard on 17/07/2016.
 */
public class DriverFactory
{
    public WebDriver create()
    {
        FirefoxDriver driver = new FirefoxDriver();

        return driver;
    }
}
