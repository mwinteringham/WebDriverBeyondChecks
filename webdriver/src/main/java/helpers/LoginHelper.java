package helpers;

import org.openqa.selenium.WebDriver;
import pageobjects.EnterBugPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;

/**
 * Created by richard on 21/07/2016.
 */
public class LoginHelper
{
    private WebDriver driver;

    public LoginHelper(WebDriver webDriver)
    {
        driver = webDriver;
    }

    public void HomePageTopLogin(String emailAddress, String password)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickLogInLink();
        mainPage.PopulateEmailAddress(emailAddress);
        mainPage.PopulatePassword(password);
        mainPage.ClickLogin();
    }

    public void LoginPageLogin(String emailAddress, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.PopulateEmail(emailAddress);
        loginPage.PopulatePassword(password);
        loginPage.ClickLoginExpectingNewBugPage();
    }
}
