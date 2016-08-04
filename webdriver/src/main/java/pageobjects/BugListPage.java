package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by richard on 16/07/2016.
 */
public class BugListPage extends PageObject
{
    @FindBy(how = How.ID, using = "search_description_controller")
    private WebElement btnToggleSearchDescription;

    @FindBy(how = How.CSS, using = "ul.search_description")
    private WebElement lblSearchDescription;

    public BugListPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_description_controller")));
    }

    public void ClickToggleSearchDescription()
    {
        btnToggleSearchDescription.click();
    }

    public boolean IsSearchDescriptionDisplayed()
    {
        return lblSearchDescription.isDisplayed();
    }
}
