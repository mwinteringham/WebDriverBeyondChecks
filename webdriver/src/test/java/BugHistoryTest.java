import databuilder.builders.BugBuilder;
import databuilder.creators.BugCreator;
import databuilder.models.BugResponseModel;
import databuilder.models.BugUpdateModel;
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
    public void BugHasHistory()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());
        BugCreator.updateBug(new BugBuilder().BuildUpdateBug(new int[]{bug.getId()}, "Updated the summary"), bug.getId());

        NavigateToBugListPage(bug.getId());
        BugHistoryPage bugHistoryPage = new BugHistoryPage(driver);
        Assert.assertThat(bugHistoryPage.IsBugActivityTableDisplayed(), is(equalTo(true)));
    }

    @Test
    public void BugHasNoHistory()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());
        NavigateToBugListPage(bug.getId());
        BugHistoryPage bugHistoryPage = new BugHistoryPage(driver);
        Assert.assertThat(bugHistoryPage.ReadNoChangesMessage(), is(equalTo("No changes have been made to this bug yet.")));
    }

    private void NavigateToBugListPage(int bugId)
    {
        driver.navigate().to(baseUrl + "show_activity.cgi?id=" + bugId);
    }
}
