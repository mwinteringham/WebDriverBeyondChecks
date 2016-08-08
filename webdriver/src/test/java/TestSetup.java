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

    @Before
    public void SetUp()
    {
        driver = new DriverFactory().create();
        baseUrl = "http://52.209.233.55:8080/bugzilla/";
    }

    @After
    public void TearDown()
    {
        driver.quit();
    }

}
