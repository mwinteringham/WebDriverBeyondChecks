package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by richard on 16/07/2016.
 */
public class LoginPage extends PageObject
{
    @FindBy(how = How.ID, using = "Bugzilla_login")
    private WebElement txtEmail;

    @FindBy(how = How.ID, using = "Bugzilla_password")
    private WebElement txtPassword;

    @FindBy(how = How.ID, using = "log_in")
    private WebElement btnLogin;

    public LoginPage(WebDriver driver)
    {
        super(driver);
    }

    public void PopulateEmail(String email)
    {
        txtEmail.sendKeys(email);
    }

    public void PopulatePassword(String password)
    {
        txtPassword.sendKeys(password);
    }

    public EnterBugPage ClickLoginExpectingNewBugPage()
    {
        btnLogin.click();
        return new EnterBugPage(driver);
    }

    public boolean IsEmailFieldDisplayed()
    {
        return txtEmail.isDisplayed();
    }
}
