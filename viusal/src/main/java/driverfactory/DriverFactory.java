package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by richard on 17/07/2016.
 */
public class DriverFactory
{
    public WebDriver create()
    {
        try{
            if(System.getenv("browser").equals("firefox")){
                return new FirefoxDriver();
            } else {
                ChromeDriver driver = generateChromeDriver();
                return driver;
            }
        } catch (NullPointerException e){
            ChromeDriver driver = generateChromeDriver();
            return driver;
        }

    }

    private ChromeDriver generateChromeDriver(){
        String pathToChromeDriver = System.getProperty("user.dir") + "/src/main/java/driverfactory/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        return new ChromeDriver();
    }
}
