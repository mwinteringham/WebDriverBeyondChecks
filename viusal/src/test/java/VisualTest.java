import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;
import helpers.LoginHelper;
import org.junit.Test;
import pageobjects.MainPage;

/**
 * Created by richard on 07/08/2016.
 */
public class VisualTest extends TestSetup
{
    @Test
    public void MainPageLayoutTest()
    {
        driver = eyes.open(driver, "BugZilla", "Main Page Layout", new RectangleSize(1024, 768));
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        eyes.checkWindow("Main Page");
    }

    @Test
    public void MainPageLayoutLoggedInTest()
    {
        driver = eyes.open(driver, "BugZilla", "Main Page Logged In Layout", new RectangleSize(1024, 768));
        driver.get(baseUrl);
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        MainPage mainPage = new MainPage(driver);
        eyes.checkWindow("Main Page Logged In");
    }
}
