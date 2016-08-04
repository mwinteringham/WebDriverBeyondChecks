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
public class CreateAccountPage extends PageObject
{
    @FindBy(how = How.ID, using = "login")
    private WebElement txtEmailAddress;

    @FindBy(how = How.ID, using = "send")
    private WebElement btnSend;

    @FindBy(how = How.CSS, using = "#bugzilla-body p")
    private WebElement lblConfirmationMessage;

    @FindBy(how = How.ID, using = "title")
    private WebElement lblTitle;

    @FindBy(how = How.ID, using = "error_msg")
    private WebElement lblErrorMessage;

    public CreateAccountPage(WebDriver driver)
    {
        super(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#bugzilla-body p")));
    }

    public void PopulateEmailAddress(String email)
    {
        txtEmailAddress.sendKeys(email);
    }

    //TODO Can't see where this page should actually go to the sign up error
    public CreateAccountPage ClickSend()
    {
        btnSend.click();
        return new CreateAccountPage(driver);
    }

    public String ReadConfirmationMessage()
    {
        return lblConfirmationMessage.getText();
    }

    public String ReadTitle()
    {
        return lblTitle.getText();
    }

    public String ReadErrorMessage()
    {
        return lblErrorMessage.getText();
    }
}
