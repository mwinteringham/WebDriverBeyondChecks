import com.applitools.eyes.Eyes;
import driverfactory.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Created by richard on 21/07/2016.
 */
public class TestSetup
{
    protected WebDriver driver;
    protected String baseUrl;
    protected Eyes eyes;

    @Before
    public void SetUp()
    {
        driver = new DriverFactory().create();
        baseUrl = "http://52.50.48.215:8080/bugzilla/";
        eyes = new Eyes();
        eyes.setApiKey("ZdP5w102YsLVHzdICTNiwXzcIlg107OF8RquYM2NYQWvOHQ110");
    }

    @After
    public void TearDown()
    {
        eyes.close();
        driver.quit();
    }

}
