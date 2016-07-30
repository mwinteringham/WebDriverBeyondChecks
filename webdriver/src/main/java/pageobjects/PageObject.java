package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by richard on 16/07/2016.
 */
public class PageObject
{
    protected WebDriver driver;

    public PageObject(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
