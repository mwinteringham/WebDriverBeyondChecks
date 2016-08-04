package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by richard on 17/07/2016.
 */
public class BugPage extends PageObject
{
    @FindBy(how = How.CSS, using = "#static_bug_status a")
    private WebElement btnEditStatus;

    @FindBy(how = How.ID, using = "static_bug_status")
    private WebElement lblStatus;

    @FindBy(how = How.ID, using = "product")
    private WebElement selProduct;

    @FindBy(how = How.ID, using = "component")
    private WebElement selComponent;

    @FindBy(how = How.CSS, using = "#bz_show_bug_column_1 > table > tbody > tr:nth-child(8) > td")
    private WebElement lblVersion;

    @FindBy(how = How.ID, using = "rep_platform")
    private WebElement selHardware;

    @FindBy(how = How.ID, using = "op_sys")
    private WebElement selOs;

    @FindBy(how = How.ID, using = "priority")
    private WebElement selPriority;

    @FindBy(how = How.ID, using = "bug_severity")
    private WebElement selSeverity;

    @FindBy(how = How.ID, using = "bz_assignee_edit_action")
    private WebElement btnEditAssignee;

    @FindBy(how = How.CSS, using = "#bz_assignee_edit_container a")
    private WebElement lblAssignee;

    @FindBy(how = How.ID, using = "bug_file_loc")
    private WebElement txtUrl;

    @FindBy(how = How.ID, using = "tag")
    private WebElement txtPersonalTags;

    @FindBy(how = How.ID, using = "dependson")
    private WebElement txtDependsOn;

    @FindBy(how = How.ID, using = "blocked")
    private WebElement txtBlocks;

    @FindBy(how = How.ID, using = "bug_status")
    private WebElement selStatus;

    @FindBy(how = How.ID, using = "commit")
    private WebElement btnSave;

    @FindBy(how = How.ID, using = "short_desc_nonedit_display")
    private WebElement lblShortDescription;

    @FindBy(how = How.ID, using = "comment_text_0")
    private WebElement lblFirstComment;

    @FindBy(how = How.LINK_TEXT, using = "Collapse All Comments")
    private WebElement btnCollapseAllComments;

    @FindBy(how = How.LINK_TEXT, using = "tag")
    private WebElement btnTagFirstComment;

    @FindBy(how = How.ID, using = "bz_ctag_add")
    private WebElement txtTag;

    @FindBy(how = How.CSS, using = "#comment_tag_0 #bz_ctag_div a")
    private WebElement btnCloseTagFirstComment;

    @FindBy(how = How.ID, using = "comment_link_0")
    private WebElement btnToggleFirstComment;

    @FindBy(how = How.ID, using = "comment")
    private WebElement txtComment;

    @FindBy(how = How.ID, using = "comment_preview_tab")
    private WebElement btnPreview;

    @FindBy(how = How.ID, using = "comment_preview_text")
    private WebElement txtPreviewText;

    @FindBy(how = How.CSS, using = "#c1 a.bz_reply_link")
    private WebElement btnReplyToFirstComment;

    public BugPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("changeform")));
    }

    public String ReadBugShortDescription()
    {
        return lblShortDescription.getText();
    }

    public boolean IsTheFirstCommentDisplayed()
    {
        return lblFirstComment.isDisplayed();
    }

    public void ClickCollapseAllComments()
    {
        btnCollapseAllComments.click();
    }

    public void ClickTagOnTheFirstComment()
    {
        btnTagFirstComment.click();
    }

    public void ClickCloseTagOnTheFirstComment()
    {
        btnCloseTagFirstComment.click();
    }

    public boolean IsTagInputDisplayed()
    {
        return txtTag.isDisplayed();
    }

    public void ClickToggleFirstComment()
    {
        btnToggleFirstComment.click();
    }

    public void PopulateComment(String comment)
    {
        txtComment.sendKeys(comment);
    }

    public String ReadCommentField()
    {
        return txtComment.getAttribute("value");
    }

    public void ClickPreviewTab()
    {
        btnPreview.click();
    }

    public String ReadPreviewText()
    {
        return txtPreviewText.getText();
    }

    public void WaitForPreviewText(String previewText)
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(txtPreviewText, previewText));
    }

    public void ClickReplyToTheFirstComment()
    {
        btnReplyToFirstComment.click();
    }
}
