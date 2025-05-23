import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {
    
    private WebDriver driver;
    private ConfigReader config = new ConfigReader();

    private ProfilePage fillLoginFormAndNavigate(){
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.fillEmailInput(config.getUsername());
        loginPage.fillPasswordInput(config.getPassword());
        ProfilePage profilePage = loginPage.pressLoginAndNavigate();
        return profilePage;
    }

    private void deleteGroupAfterAssertion(GroupsPage groupsPage){
        groupsPage.checkCheckbox();
        groupsPage.clickBinIcon();
        groupsPage.clickDeleteButton();
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // making Chrome faster (?)
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test
    public void ReadingPageTitle(){
        LandingPage landingPage = new LandingPage(this.driver);
        Assert.assertTrue(this.driver.getTitle().contains("MisEnapló"));
    }

    @Test
    public void StaticTestOfLandingPageElements(){
        LandingPage landingPage = new LandingPage(this.driver);
        for (String locatorString : landingPage.locatorsOfLandingPage) {
            Assert.assertTrue(landingPage.elementDisplayed(locatorString));
        }
    }

    @Test(dependsOnMethods = "ReadingPageTitle")
    public void SuccessfulLogin(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        Assert.assertTrue(profilePage.getHeadingText().contains(("Felhasználói profil").toUpperCase()));
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void Logout(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        LandingPage landingPage = profilePage.pressLogoutAndNavigate();
        Assert.assertTrue(landingPage.getHeadingText().contains(("Kezdőlap").toUpperCase()));
    }

    @Test(dependsOnMethods = {"SuccessfulLogin", "HoverTestOnMultiplePages"})
    public void FillAddGroupForm(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        GroupsPage groupsPage = profilePage.navigateToGroupsPage();
        groupsPage.checkCheckbox();
        groupsPage.clickOnAddButton();
        Assert.assertTrue(groupsPage.getDialogTitle().contains("Új csoport"));
        groupsPage.fillNameInput();
        groupsPage.saveGroup();
        Assert.assertTrue(groupsPage.isGroupNameInList());
        deleteGroupAfterAssertion(groupsPage);
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void StaticTestOfScanPage(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        ScanPage scanPage = profilePage.navigateToScanPage();
        Assert.assertTrue(scanPage.hasHeader());
        Assert.assertTrue(scanPage.hasImage());
        Assert.assertTrue(scanPage.hasDropdown());
        Assert.assertTrue(scanPage.hasFooter());
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void HoverTestOnMultiplePages(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        GroupsPage groupsPage = profilePage.navigateToGroupsPage();
        Assert.assertTrue(groupsPage.hoverOnAddButtonShowsTooltip());
        UsersPage usersPage = profilePage.navigateToUsersPage();
        Assert.assertTrue(usersPage.hoverOnAddButtonShowsTooltip());
        ParishesPage parishesPage = usersPage.navigateToParishesPage();
        Assert.assertTrue(parishesPage.hoverOnAddButtonShowsTooltip());
    }

    @AfterMethod
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
