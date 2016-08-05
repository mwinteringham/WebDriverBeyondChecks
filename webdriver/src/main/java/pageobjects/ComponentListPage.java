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
public class ComponentListPage extends PageObject
{

    @FindBy(how = How.ID, using = "admin_table")
    private WebElement tblComponents;

    @FindBy(how = How.LINK_TEXT, using = "Add")
    private WebElement btnAdd;

    public ComponentListPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("admin_table")));
    }

    public String ReadTableContents()
    {
        return tblComponents.getText();
    }

    public AddComponentPage ClickAdd()
    {
        btnAdd.click();
        return new AddComponentPage(driver);
    }
}
