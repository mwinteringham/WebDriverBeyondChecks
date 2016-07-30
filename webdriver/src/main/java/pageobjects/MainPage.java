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
public class MainPage extends PageObject
{
    @FindBy(how = How.LINK_TEXT, using = "Home")
    private WebElement linkHome;

    @FindBy(how = How.LINK_TEXT, using = "New")
    private WebElement linkNew;

    @FindBy(how = How.LINK_TEXT, using = "Browse")
    private WebElement linkBrowse;

    @FindBy(how = How.LINK_TEXT, using = "Search")
    private WebElement linkSearch;

    @FindBy(how = How.ID, using = "quicksearch_top")
    private WebElement txtTopSearch;

    @FindBy(how = How.ID, using = "find_top")
    private WebElement btnTopSearch;

    @FindBy(how = How.LINK_TEXT, using = "Reports")
    private WebElement linkReports;

    @FindBy(how = How.LINK_TEXT, using = "New Account")
    private WebElement linkNewAccount;

    @FindBy(how = How.LINK_TEXT, using = "Log In")
    private WebElement linkLogin;

    @FindBy(how = How.LINK_TEXT, using = "Forget Password")
    private WebElement linkForgotPassword;

    @FindBy(how = How.ID, using = "enter_bug")
    private WebElement btnFileABug;

    @FindBy(how = How.ID, using = "query")
    private WebElement btnSearch;

    @FindBy(how = How.ID, using = "account")
    private WebElement btnOpenANewAccount;

    @FindBy(how = How.ID, using = "help")
    private WebElement btnDocumentation;

    @FindBy(how = How.ID, using = "quicksearch_main")
    private WebElement txtSearch;

    @FindBy(how = How.ID, using = "find")
    private WebElement btnQuickSearch;

    @FindBy(how = How.ID, using = "Bugzilla_login_top")
    private WebElement txtEmailAddress;

    @FindBy(how = How.ID, using = "Bugzilla_password_top")
    private WebElement txtPassword;

    @FindBy(how = How.ID, using = "log_in_top")
    private WebElement btnLogin;

    @FindBy(how = How.ID, using = "login_top")
    public WebElement txtForgotPasswordEmail;

    @FindBy(how = How.ID, using = "forgot_button_top")
    public WebElement btnResetPassword;

    public MainPage(WebDriver driver)
    {
        super(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("enter_bug")));
    }

    public MainPage ClickHome()
    {
        linkHome.click();
        return new MainPage(driver);
    }

    public LoginPage ClickNewExpectingLogin()
    {
        linkNew.click();
        return new LoginPage(driver);
    }

    public ComponentsPage ClickBrowse()
    {
        linkBrowse.click();
        return new ComponentsPage(driver);
    }

    public SimpleSearchPage ClickSearchLink()
    {
        linkSearch.click();
        return new SimpleSearchPage(driver);
    }

    public BugListPage ClickTopSearch()
    {
        btnTopSearch.click();
        return new BugListPage(driver);
    }

    public void PopulateTopSearchField(String searchTerm)
    {
        txtTopSearch.sendKeys(searchTerm);
    }

    public ReportingAndChartingKitchenPage ClickReports()
    {
        linkReports.click();
        return new ReportingAndChartingKitchenPage(driver);
    }

    public CreateAccountPage ClickNewAccount()
    {
        linkNewAccount.click();
        return new CreateAccountPage(driver);
    }

    public void ClickLogInLink()
    {
        linkLogin.click();
    }

    public void PopulateEmailAddress(String email)
    {
        txtEmailAddress.sendKeys(email);
    }

    public void PopulatePassword(String password)
    {
        txtPassword.sendKeys(password);
    }

    public InvalidLoginPage ClickLoginExpectingError()
    {
        btnLogin.click();
        return new InvalidLoginPage(driver);
    }

    public MainPage ClickLogin()
    {
        btnLogin.click();
        return new MainPage(driver);
    }

    public void ClickForgotPassword()
    {
        linkForgotPassword.click();
    }

    public RequestToChangePasswordPage ClickResetPassword()
    {
        btnResetPassword.click();
        return new RequestToChangePasswordPage(driver);
    }

    public void PopulateForgotPasswordEmail(String email)
    {
        txtForgotPasswordEmail.sendKeys(email);
    }

    public LoginPage ClickFileABugExpectingLogin()
    {
        btnFileABug.click();
        return new LoginPage(driver);
    }

    public EnterBugPage ClickFileABug()
    {
        btnFileABug.click();
        return new EnterBugPage(driver);
    }

    public SimpleSearchPage ClickSearchButton()
    {
        btnSearch.click();
        return new SimpleSearchPage(driver);
    }

    public CreateAccountPage ClickOpenANewAccountButton()
    {
        btnOpenANewAccount.click();
        return new CreateAccountPage(driver);
    }
}
