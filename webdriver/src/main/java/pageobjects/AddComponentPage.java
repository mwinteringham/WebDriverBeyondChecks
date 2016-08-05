package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by richard on 05/08/2016.
 */
public class AddComponentPage extends PageObject
{
    @FindBy(how = How.ID, using = "component")
    private WebElement txtComponentName;

    @FindBy(how = How.ID, using = "description")
    private WebElement txtComponentDescription;

    @FindBy(how = How.ID, using = "initialowner")
    private WebElement txtDefaultAssignee;

    @FindBy(how = How.ID, using = "create")
    private WebElement btnCreate;

    public AddComponentPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("component")));
    }

    public void PopulateComponentName(String name)
    {
        txtComponentName.sendKeys(name);
    }

    public void PopulateComponentDescription(String description)
    {
        txtComponentDescription.sendKeys(description);
    }

    public void PopulateDefaultAssignee(String assignee)
    {
        txtDefaultAssignee.sendKeys(assignee);
    }

    public ComponentListPage ClickAdd()
    {
        btnCreate.click();
        return new ComponentListPage(driver);
    }
}

