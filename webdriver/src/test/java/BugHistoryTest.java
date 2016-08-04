import org.junit.Assert;
import org.junit.Test;
import pageobjects.BugHistoryPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 04/08/2016.
 */
public class BugHistoryTest extends TestSetup
{
    @Test
    //TODO Create a bug that has history
    public void BugHasHistory()
    {
        NavigateToBugListPage(3);
        BugHistoryPage bugHistoryPage = new BugHistoryPage(driver);
        Assert.assertThat(bugHistoryPage.IsBugActivityTableDisplayed(), is(equalTo(true)));
    }

    @Test
    //TODO Create a bug that has no history, e.g new bug
    public void BugHasNoHistory()
    {
        NavigateToBugListPage(8);
        BugHistoryPage bugHistoryPage = new BugHistoryPage(driver);
        Assert.assertThat(bugHistoryPage.ReadNoChangesMessage(), is(equalTo("No changes have been made to this bug yet.")));
    }

    private void NavigateToBugListPage(int bugId)
    {
        driver.navigate().to(baseUrl + "show_activity.cgi?id=" + bugId);
    }
}
