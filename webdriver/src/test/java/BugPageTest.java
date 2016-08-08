import databuilder.builders.BugBuilder;
import databuilder.creators.BugCreator;
import databuilder.models.BugCommentModel;
import databuilder.models.BugModel;
import databuilder.models.BugResponseModel;
import helpers.LoginHelper;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.BugPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 03/08/2016.
 */
public class BugPageTest extends TestSetup
{
    @Test
    //TODO Hug into the bugd data creation API
    public void CheckAllBugValues()
    {
        BugModel bug = new BugBuilder().BuildBasicNewBug();
        BugResponseModel bugResponse = BugCreator.createBug(bug);

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bugResponse.getId());
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.ReadStatus(), is(equalTo("CONFIRMED (edit)")));
        Assert.assertThat(bugPage.ReadProduct(), is(equalTo(bug.getProduct())));
        Assert.assertThat(bugPage.ReadComponent(), is(equalTo(bug.getComponent())));
        Assert.assertThat(bugPage.ReadHardware(), is(equalTo(bug.getRep_platform())));
        Assert.assertThat(bugPage.ReadOS(), is(equalTo(bug.getOp_sys())));
        Assert.assertThat(bugPage.ReadSeverity(), is(equalTo("enhancement")));
        Assert.assertThat(bugPage.ReadDescription(), is(equalTo(bug.getDescription())));
    }

    @Test
    public void InvalidBugId()
    {
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(33333);
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.ReadErrorMessage(), is(equalTo("You must enter a valid bug number!")));
    }

    @Test
    public void InvalidBugIdStyling()
    {
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(33333);
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.ReadErrorMessageClass(), is(equalTo("throw_error")));
    }

    @Test
    public void ExpandCollapseComments()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(true)));
        bugPage.ClickCollapseAllComments();
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(false)));
    }

    @Test
    public void ShowCloseTagFieldOnComment()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.IsTagInputDisplayed(), is(equalTo(false)));
        bugPage.ClickTagOnTheFirstComment();
        Assert.assertThat(bugPage.IsTagInputDisplayed(), is(equalTo(true)));
        bugPage.ClickCloseTagOnTheFirstComment();
        Assert.assertThat(bugPage.IsTagInputDisplayed(), is(equalTo(false)));
    }

    @Test
    public void AddATagToTheFirstComment()
    {
        String tag = "test";
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());
        BugCreator.createBugComment(new BugBuilder().BuildBugComment("This is a comment"), bug.getId());

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        bugPage.ClickTagOnTheFirstComment();
        bugPage.PopulateTagOnFirstComment(tag);
        bugPage.PressEnterInTagInput();
        Assert.assertThat(bugPage.ReadTagOnFirstComment(), is(equalTo("x " + tag)));
        bugPage.ClickXOnTagOnFirstComment();
    }

    @Test
    public void ToggleFirstComment()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());
        BugCreator.createBugComment(new BugBuilder().BuildBugComment("This is a comment"), bug.getId());

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(true)));
        bugPage.ClickToggleFirstComment();
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(false)));
        bugPage.ClickToggleFirstComment();
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(true)));
    }

    @Test
    public void CheckCommentPreview()
    {
        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());

        String commentText = "This is a comment";
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        bugPage.PopulateComment(commentText);
        bugPage.ClickPreviewTab();
        bugPage.WaitForPreviewText(commentText);
        Assert.assertThat(bugPage.ReadPreviewText(), is(equalTo(commentText)));
    }

    @Test
    public void ReplyToAComment()
    {
        String originalCommentText = "comment goes here";

        BugResponseModel bug = BugCreator.createBug(new BugBuilder().BuildBasicNewBug());
        BugCreator.createBugComment(new BugBuilder().BuildBugComment(originalCommentText), bug.getId());

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(bug.getId());
        BugPage bugPage = new BugPage(driver);
        bugPage.ClickReplyToTheFirstComment();
        Assert.assertThat(bugPage.ReadCommentField(), is(equalTo("(In reply to Admin from comment #1)\n> " + originalCommentText + "\n\n")));
    }

    private void NavigateToHomePage()
{
    driver.navigate().to(baseUrl);
}
    private void NavigateToBug(int bugId)
    {
        driver.navigate().to(baseUrl + "show_bug.cgi?id=" + bugId);
    }
}
