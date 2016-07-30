import driverfactory.DriverFactory;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.EnterBugPage;
import pageobjects.MainPage;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by richard on 17/07/2016.
 */
public class PlayGround
{
    @Test
    public void TestTest() throws MalformedURLException
    {
        WebDriver driver = new DriverFactory().create();

        driver.navigate().to(new URL("http://localhost:8080/bugzilla"));
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickLogInLink();
        mainPage.PopulateEmailAddress("admin@bugzilla.org");
        mainPage.PopulatePassword("password");
        mainPage = mainPage.ClickLogin();
        EnterBugPage enterBugPage = mainPage.ClickFileABug();
        enterBugPage.SelectComponent("TestComponent");
        enterBugPage.SelectVersion("unspecified");
        enterBugPage.SelectSeverity("critical");
        enterBugPage.SelectHardware("PC");
        enterBugPage.SelectOS("Mac OS");
        enterBugPage.PopulateSummary("Test 1");
        enterBugPage.PopulateDescription("Description 1");
        enterBugPage.ClickSubmitBug();
    }
}
