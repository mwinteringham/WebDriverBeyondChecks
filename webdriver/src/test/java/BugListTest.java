import org.junit.Assert;
import org.junit.Test;
import pageobjects.BugListPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 04/08/2016.
 */
public class BugListTest extends TestSetup
{
    @Test
    public void ToggleSearchDescription()
    {
        NavigateToBugListPage();
        BugListPage bugListPage = new BugListPage(driver);
        Assert.assertThat(bugListPage.IsSearchDescriptionDisplayed(), is(equalTo(true)));
        bugListPage.ClickToggleSearchDescription();
        Assert.assertThat(bugListPage.IsSearchDescriptionDisplayed(), is(equalTo(false)));
    }

    private void NavigateToBugListPage()
    {
        driver.navigate().to(baseUrl + "buglist.cgi?bug_status=__open__&no_redirect=1&order=Importance&query_format=specific");
    }
}
