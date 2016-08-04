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
