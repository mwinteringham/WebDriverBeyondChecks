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
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.ReadStatus(), is(equalTo("CONFIRMED (edit)")));
        Assert.assertThat(bugPage.ReadProduct(), is(equalTo("TestProduct")));
        Assert.assertThat(bugPage.ReadComponent(), is(equalTo("TestComponent")));
        Assert.assertThat(bugPage.ReadHardware(), is(equalTo("PC")));
        Assert.assertThat(bugPage.ReadOS(), is(equalTo("Mac OS")));
        Assert.assertThat(bugPage.ReadSeverity(), is(equalTo("minor")));
        Assert.assertThat(bugPage.ReadDescription(), is(equalTo("This is a minor description")));
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
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
        BugPage bugPage = new BugPage(driver);
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(true)));
        bugPage.ClickCollapseAllComments();
        Assert.assertThat(bugPage.IsTheFirstCommentDisplayed(), is(equalTo(false)));
    }

    @Test
    public void ShowCloseTagFieldOnComment()
    {
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
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
        String tag = "Test";

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
        BugPage bugPage = new BugPage(driver);
        bugPage.ClickTagOnTheFirstComment();
        bugPage.PopulateTagOnFirstComment(tag);
        bugPage.PressEnterInTagInput();
        Assert.assertThat(bugPage.ReadTagOnFirstComment(), is(equalTo("x " + tag)));
    }

    @Test
    public void ToggleFirstComment()
    {
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
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
        String commentText = "This is a comment";
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
        BugPage bugPage = new BugPage(driver);
        bugPage.PopulateComment(commentText);
        bugPage.ClickPreviewTab();
        bugPage.WaitForPreviewText(commentText);
        Assert.assertThat(bugPage.ReadPreviewText(), is(equalTo(commentText)));
    }

    @Test
    //TODO Requires a bug with a comment on it, and to know it's text
    public void ReplyToAComment()
    {
        String originalCommentTezt = "dfgdg";

        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        NavigateToBug(3);
        BugPage bugPage = new BugPage(driver);
        bugPage.ClickReplyToTheFirstComment();
        Assert.assertThat(bugPage.ReadCommentField(), is(equalTo("(In reply to Admin from comment #1)\n> " + originalCommentTezt + "\n\n")));
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
