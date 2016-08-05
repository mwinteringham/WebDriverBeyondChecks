import helpers.LoginHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.AddComponentPage;
import pageobjects.ComponentListPage;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by richard on 05/08/2016.
 */
public class ComponentAdminTest extends TestSetup
{
    @Test
    public void AddANewComponent()
    {
        NavigateToAddComponentPage();
        LoginHelper loginHelper = new LoginHelper(driver);
        loginHelper.LoginPageLogin("admin@bugzilla.org", "password");
        ComponentListPage componentListPage = new ComponentListPage(driver);
        AddComponentPage addComponentPage = componentListPage.ClickAdd();
        String componentName = RandomStringUtils.random(10, true, false);
        addComponentPage.PopulateComponentName(componentName);
        addComponentPage.PopulateComponentDescription(RandomStringUtils.random(50, true, false));
        addComponentPage.PopulateDefaultAssignee("admin@bugzilla.org");
        componentListPage = addComponentPage.ClickAdd();
        Assert.assertThat(componentListPage.ReadTableContents(), containsString(componentName));
    }

    public void NavigateToAddComponentPage()
    {
        driver.navigate().to(baseUrl + "editcomponents.cgi?product=TestProduct");
    }
}
