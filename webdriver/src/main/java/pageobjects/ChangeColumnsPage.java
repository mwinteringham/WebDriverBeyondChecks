package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.in;

/**
 * Created by richard on 03/08/2016.
 */
public class ChangeColumnsPage extends PageObject
{
    @FindBy(how = How.ID, using = "available_columns")
    private WebElement selAvailableColumns;

    @FindBy(how = How.ID, using = "selected_columns")
    private WebElement selSelectedColumns;

    @FindBy(how = How.ID, using = "select_button")
    private WebElement btnMoveOptionToSelected;

    @FindBy(how = How.ID, using = "deselect_button")
    private WebElement btnMoveOptionToAvailable;

    @FindBy(how = How.ID, using = "up_button")
    private WebElement btnMoveSelectedOptionUp;

    @FindBy(how = How.ID, using = "down_button")
    private WebElement btnMoveSelectedOptionDown;

    public ChangeColumnsPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("available_columns")));
    }

    public void SelectAnAvailableColumnOption(String columnName)
    {
        Select select = new Select(selAvailableColumns);
        select.selectByVisibleText(columnName);
    }

    public void SelectAnSelectedlableColumnOption(String columnName)
    {
        Select select = new Select(selSelectedColumns);
        select.selectByVisibleText(columnName);
    }

    public void ClickMoveToSelectedColumns()
    {
        btnMoveOptionToSelected.click();
    }

    public void ClickMoveToAvailableColumns()
    {
        btnMoveOptionToAvailable.click();
    }

    public void ClickMoveSelectedOptionUp()
    {
        btnMoveSelectedOptionUp.click();
    }

    public void ClickMoveSelectedOptionDown()
    {
        btnMoveSelectedOptionDown.click();
    }

    public List<String> GetAvailableColumnOptions()
    {
        List<String> availableColumnOptions = new ArrayList<String>();

        Select select = new Select(selAvailableColumns);
        List<WebElement> options = select.getOptions();

        for(WebElement option : options)
        {
            availableColumnOptions.add(option.getText());
        }

        return availableColumnOptions;
    }

    public List<String> GetSelectedColumnOptions()
    {
        List<String> selectedColumnOptions = new ArrayList<String>();

        Select select = new Select(selSelectedColumns);
        List<WebElement> options = select.getOptions();

        for(WebElement option : options)
        {
            selectedColumnOptions.add(option.getText().trim());
        }

        return selectedColumnOptions;
    }

    public int GetIndexOfSelectedOption(String optionName)
    {
        return GetSelectedColumnOptions().indexOf(optionName);
    }
}
