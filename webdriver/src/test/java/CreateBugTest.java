import helpers.LoginHelper;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.BugPage;
import pageobjects.EnterBugPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 23/07/2016.
 */
public class CreateBugTest extends TestSetup
{
    @Test
    public void CreateBasicBug()
    {
        driver.navigate().to(baseUrl + "/enter_bug.cgi");
        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.LoginPageLogin("admin@bugzilla.org", "password");
        EnterBugPage enterBugPage = new EnterBugPage(driver);
        enterBugPage.SelectComponent("TestComponent");
        enterBugPage.SelectVersion("unspecified");
        enterBugPage.SelectSeverity("minor");
        enterBugPage.SelectHardware("PC");
        enterBugPage.SelectOS("Mac OS");
        enterBugPage.PopulateSummary("This is a minor bug");
        enterBugPage.PopulateDescription("This is a minor description");
        BugPage bugPage = enterBugPage.ClickSubmitBug();
        Assert.assertThat(bugPage.ReadBugShortDescription(), is(equalTo("This is a minor bug")));
    }

    @Test
    public void ShowAdvancedFieldsAreShown()
    {
        driver.navigate().to(baseUrl + "/enter_bug.cgi");
        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.LoginPageLogin("admin@bugzilla.org", "password");
        EnterBugPage enterBugPage = new EnterBugPage(driver);
        Assert.assertThat(enterBugPage.IsStatusDropdownDisplayed(), is(equalTo(false)));
        enterBugPage.ClickShowAdvancedFields();
        Assert.assertThat(enterBugPage.IsStatusDropdownDisplayed(), is(equalTo(true)));
    }

    @Test
    public void CalenderShowsForDeadlineAdvancedOption()
    {
        driver.navigate().to(baseUrl + "/enter_bug.cgi");
        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.LoginPageLogin("admin@bugzilla.org", "password");
        EnterBugPage enterBugPage = new EnterBugPage(driver);
        enterBugPage.ClickShowAdvancedFields();
        Assert.assertThat(enterBugPage.IsDeadlineCalendarDisplayed(), is(equalTo(false)));
        enterBugPage.ClickDeadlineCalendar();
        Assert.assertThat(enterBugPage.IsDeadlineCalendarDisplayed(), is(equalTo(true)));
        enterBugPage.ClickDeadlineCalendar();
        Assert.assertThat(enterBugPage.IsDeadlineCalendarDisplayed(), is(equalTo(false)));
    }
}
