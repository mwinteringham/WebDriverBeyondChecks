import helpers.LoginHelper;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.LoginPage;
import pageobjects.MainPage;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 21/07/2016.
 */
public class HomePageTest extends TestSetup
{
    private void NavigateToHomePage()
    {
        driver.navigate().to(baseUrl);
    }

    @Test
    public void NewButtonNavigationNotLoggedIn()
    {
        NavigateToHomePage();
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.ClickNewExpectingLogin();
        Assert.assertThat(loginPage.IsEmailFieldDisplayed(), is(equalTo(true)));
    }

    @Test
    public void TestLoginButtonShowsLoginForm()
    {
        NavigateToHomePage();
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickLogInLink();
        Assert.assertThat(mainPage.IsLoginEmailAddressFieldDisplayed(), is(equalTo(true)));
    }

    @Test
    public void TestCloseButtonClosesLoginForm()
    {
        NavigateToHomePage();
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickLogInLink();
        mainPage.PopulateEmailAddress("test@test.com");
        mainPage.ClickCloseTopForm();
        Assert.assertThat(mainPage.IsLoginEmailAddressFieldDisplayed(), is(equalTo(false)));
    }

    @Test
    public void TestForgotPasswordShowsRecoveryForm()
    {
        NavigateToHomePage();
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickForgotPassword();
        Assert.assertThat(mainPage.IsRecoveryEmailAddressFieldDisplayed(), is(equalTo(true)));
    }

    @Test
    public void TestCloseButtonClosesRecoveryForm()
    {
        NavigateToHomePage();
        MainPage mainPage = new MainPage(driver);
        mainPage.ClickForgotPassword();
        mainPage.ClickCloseTopForm();
        Assert.assertThat(mainPage.IsRecoveryEmailAddressFieldDisplayed(), is(equalTo(false)));
    }

    @Test
    public void Login()
    {
        NavigateToHomePage();
        new LoginHelper(driver).HomePageTopLogin("admin@bugzilla.org", "password");
        MainPage mainPage = new MainPage(driver);
        Assert.assertThat(mainPage.IsLogoutButtonDisplayed(), is(equalTo(true)));
    }
}
