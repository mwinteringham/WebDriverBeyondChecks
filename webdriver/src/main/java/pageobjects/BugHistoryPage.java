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
public class BugHistoryPage extends PageObject
{
    @FindBy(how = How.CSS, using = "#bugzilla-body p:nth-child(2)")
    private WebElement lblNoChanges;

    @FindBy(how = How.ID, using = "bug_activity")
    private WebElement tblBugActivity;

    public BugHistoryPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#bugzilla-body p a")));
    }

    public String ReadNoChangesMessage()
    {
        return lblNoChanges.getText();
    }

    public boolean IsBugActivityTableDisplayed()
    {
        return tblBugActivity.isDisplayed();
    }
}
