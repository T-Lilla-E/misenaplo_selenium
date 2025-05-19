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

    @BeforeMethod
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        // making Chrome faster (?)
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @Test()
    public void StaticTestOfLandingPageElements(){
        LandingPage landingPage = new LandingPage(this.driver);
        for (String locatorString : landingPage.locatorsOfLandingPage) {
            Assert.assertTrue(landingPage.elementDisplayed(locatorString));
        }
    }

    @Test
    public void SuccessfulLogin(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        Assert.assertTrue(profilePage.getTitle().contains(("Felhasználói profil").toUpperCase()));
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void Logout(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        LandingPage landingPage = profilePage.pressLogoutAndNavigate();
        Assert.assertTrue(landingPage.getTitle().contains(("Kezdőlap").toUpperCase()));
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void FillAddGroupForm(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        GroupsPage groupsPage = profilePage.navigateToGroupsPage();
        groupsPage.checkCheckbox();
        groupsPage.clickOnAddButton();
        Assert.assertTrue(groupsPage.getDialogTitle().contains("Új csoport"));
        groupsPage.fillNameInput();
        groupsPage.saveGroup();
        //Assert.assertTrue(this.driver.getPageSource().contains("my group"));
        Assert.assertTrue(groupsPage.isGroupNameInList());
    }

    @AfterMethod
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
