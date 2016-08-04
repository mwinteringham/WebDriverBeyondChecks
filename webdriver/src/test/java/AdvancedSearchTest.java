import org.junit.Assert;
import org.junit.Test;
import pageobjects.AdvancedSearchPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 04/08/2016.
 */
public class AdvancedSearchTest extends TestSetup
{
    @Test
    public void ExpandDetailedBugInformation()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        Assert.assertThat(advancedSearchPage.IsDetailBugInformationExpanded(), is(equalTo(false)));
        advancedSearchPage.ClickDetailedBugInformation();
        Assert.assertThat(advancedSearchPage.IsDetailBugInformationExpanded(), is(equalTo(true)));
    }

    @Test
    public void ExpandSearchByPeople()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        Assert.assertThat(advancedSearchPage.IsSearchByPeopleExpanded(), is(equalTo(false)));
        advancedSearchPage.ClickSearchByPeople();
        Assert.assertThat(advancedSearchPage.IsSearchByPeopleExpanded(), is(equalTo(true)));
    }

    @Test
    public void ExpandSearchByChangeHistory()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        Assert.assertThat(advancedSearchPage.IsSearchByChangeHistoryExpanded(), is(equalTo(false)));
        advancedSearchPage.ClickSearchByChangeHistory();
        Assert.assertThat(advancedSearchPage.IsSearchByChangeHistoryExpanded(), is(equalTo(true)));
    }

    @Test
    public void ExpandCustomSearch()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        Assert.assertThat(advancedSearchPage.IsCustomSearchExpanded(), is(equalTo(false)));
        advancedSearchPage.ClickCustomSearch();
        Assert.assertThat(advancedSearchPage.IsCustomSearchExpanded(), is(equalTo(true)));
    }

    @Test
    public void AddRowToCustomSearch()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        advancedSearchPage.ClickCustomSearch();
        advancedSearchPage.ClickAddRowToCustomSearch();
        Assert.assertThat(advancedSearchPage.IsQueryStringRowTwoDisplayedInCustomSearch(), is(equalTo(true)));
    }

    @Test
    public void OpenAndCloseParenthesisOnCustomSearch()
    {
        NavigateToAdvancedSearch();
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);
        advancedSearchPage.ClickCustomSearch();
        advancedSearchPage.ClickShowAdvancedFeaturesInCustomSearch();
        advancedSearchPage.ClickOpenParenthesis();
        Assert.assertThat(advancedSearchPage.IsQueryStringRowTwoDisplayedInCustomSearch(), is(equalTo(true)));
        advancedSearchPage.ClickCloseParenthesis();
        Assert.assertThat(advancedSearchPage.IsQueryStringRowThreeDisplayedInCustomSearch(), is(equalTo(true)));
    }

    private void NavigateToAdvancedSearch()
    {
        driver.navigate().to(baseUrl + "query.cgi?format=advanced");
    }
}
