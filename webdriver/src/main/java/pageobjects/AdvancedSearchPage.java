package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by richard on 04/08/2016.
 */
public class AdvancedSearchPage extends PageObject
{
    @FindBy(how = How.CSS, using = "#detailed_information a")
    private WebElement btnDetailedBugInformation;

    @FindBy(how = How.CSS, using = "#people_filter a")
    private WebElement btnSearchByPeople;

    @FindBy(how = How.CSS, using = "#history_filter a")
    private WebElement btnSearchByChangeHistory;

    @FindBy(how = How.CSS, using = "#custom_search_filter a")
    private WebElement btnCustomSearch;

    @FindBy(how = How.ID, using = "longdesc")
    private WebElement txtComment;

    @FindBy(how = How.ID, using = "email2")
    private WebElement selEmail;

    @FindBy(how = How.ID, using = "chfield")
    private WebElement selChangeHistoryFields;

    @FindBy(how = How.ID, using = "j_top")
    private WebElement selMatch;

    @FindBy(how = How.ID, using = "add_button")
    private WebElement btnAddCondition;

    @FindBy(how = How.ID, using = "f2")
    private WebElement selQueryStringRowTwo;

    @FindBy(how = How.ID, using = "f4")
    private WebElement selQueryStringRowThree;

    @FindBy(how = How.ID, using = "op_button")
    private WebElement btnOpenParenthesis;

    @FindBy(how = How.ID, using = "cp_button")
    private WebElement btnCloseParenthesis;

    @FindBy(how = How.ID, using = "custom_search_advanced_controller")
    private WebElement btnShowAdvancedFeatures;

    public AdvancedSearchPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("information_query_controller")));
    }

    public void ClickDetailedBugInformation()
    {
        btnDetailedBugInformation.click();
    }

    public void ClickSearchByPeople()
    {
        btnSearchByPeople.click();
    }

    public void ClickSearchByChangeHistory()
    {
        btnSearchByChangeHistory.click();
    }

    public void ClickCustomSearch()
    {
        btnCustomSearch.click();
    }

    public boolean IsDetailBugInformationExpanded()
    {
        return txtComment.isDisplayed();
    }

    public boolean IsSearchByPeopleExpanded()
    {
        return selEmail.isDisplayed();
    }

    public boolean IsSearchByChangeHistoryExpanded()
    {
        return selChangeHistoryFields.isDisplayed();
    }

    public boolean IsCustomSearchExpanded()
    {
        return selMatch.isDisplayed();
    }

    public boolean IsQueryStringRowTwoDisplayedInCustomSearch()
    {
        return selQueryStringRowTwo.isDisplayed();
    }

    public boolean IsQueryStringRowThreeDisplayedInCustomSearch()
    {
        return selQueryStringRowThree.isDisplayed();
    }

    public void ClickAddRowToCustomSearch()
    {
        btnAddCondition.click();
    }

    public void ClickOpenParenthesis()
    {
        btnOpenParenthesis.click();
    }

    public void ClickCloseParenthesis()
    {
        btnCloseParenthesis.click();
    }

    public void ClickShowAdvancedFeaturesInCustomSearch()
    {
        btnShowAdvancedFeatures.click();
    }
}
