import org.junit.Assert;
import org.junit.Test;
import pageobjects.ChangeColumnsPage;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 03/08/2016.
 */
public class ChangeColumnTest extends TestSetup
{
    @Test
    public void MoveOptionToSelectedColumns()
    {
        driver.navigate().to(baseUrl + "/colchange.cgi");
        ChangeColumnsPage changeColumnsPage = new ChangeColumnsPage(driver);
        Assert.assertThat(changeColumnsPage.GetSelectedColumnOptions(), not(hasItem("Hardware")));
        changeColumnsPage.SelectAnAvailableColumnOption("Hardware");
        changeColumnsPage.ClickMoveToSelectedColumns();
        Assert.assertThat(changeColumnsPage.GetSelectedColumnOptions(), hasItem("Hardware"));
    }

    @Test
    public void MoveOptionToAvailableColumns()
    {
        driver.navigate().to(baseUrl + "/colchange.cgi");
        ChangeColumnsPage changeColumnsPage = new ChangeColumnsPage(driver);
        Assert.assertThat(changeColumnsPage.GetAvailableColumnOptions(), not(hasItem("Status")));
        changeColumnsPage.SelectAnSelectedlableColumnOption("Status");
        changeColumnsPage.ClickMoveToAvailableColumns();
        Assert.assertThat(changeColumnsPage.GetAvailableColumnOptions(), hasItem("Status"));
    }

    @Test
    public void MoveSelectedOptionUp()
    {
        driver.navigate().to(baseUrl + "/colchange.cgi");
        ChangeColumnsPage changeColumnsPage = new ChangeColumnsPage(driver);
        int startOptionIndex = changeColumnsPage.GetIndexOfSelectedOption("Status");
        changeColumnsPage.SelectAnSelectedlableColumnOption("Status");
        changeColumnsPage.ClickMoveSelectedOptionUp();
        int endOptionIndex = changeColumnsPage.GetIndexOfSelectedOption("Status");
        Assert.assertThat(endOptionIndex, is(equalTo(startOptionIndex - 1)));
    }

    @Test
    public void MoveSelectedOptionDown()
    {
        driver.navigate().to(baseUrl + "/colchange.cgi");
        ChangeColumnsPage changeColumnsPage = new ChangeColumnsPage(driver);
        int startOptionIndex = changeColumnsPage.GetIndexOfSelectedOption("Status");
        changeColumnsPage.SelectAnSelectedlableColumnOption("Status");
        changeColumnsPage.ClickMoveSelectedOptionDown();
        int endOptionIndex = changeColumnsPage.GetIndexOfSelectedOption("Status");
        Assert.assertThat(endOptionIndex, is(equalTo(startOptionIndex + 1)));
    }
}
