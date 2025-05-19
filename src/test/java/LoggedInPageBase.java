import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class LoggedInPageBase extends PageBase {

    protected By logoutLocator = By.xpath("//button[span[contains(., \"Kijelentkezés\")]]");

    protected By profileLocator = By.xpath("//a[@href=\"/profile\"]");
    protected By groupsLocator = By.xpath("//a[@href=\"/groups\"]");
    protected By usersLocator = By.xpath("//a[@href=\"/users\"]");
    protected By parishesLocator = By.xpath("//a[@href=\"/parishes\"]");
    protected By scanTaskLocator = By.xpath("//a[@href=\"/scanTask\"]");
    
    private By locatorOfMirjam = By.xpath("//h1[contains(., \"Betlehemi Szent Mirjam\")]");
    
    public LoggedInPageBase(WebDriver driver){
        super(driver);
    }

    public LandingPage pressLogoutAndNavigate(){
        pressButtonFromSideMenu(logoutLocator);
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locatorOfMirjam));
        //this.wait.until(ExpectedConditions.urlToBe("https://misenaplo.hu"));
        return new LandingPage(this.driver);
    }

    public ProfilePage navigateToProfilePage(){
        pressButtonFromSideMenu(profileLocator);
        this.wait.until(ExpectedConditions.urlContains("/profile"));
        return new ProfilePage(this.driver);
    }

    public GroupsPage navigateToGroupsPage(){
        pressButtonFromSideMenu(groupsLocator);
        this.wait.until(ExpectedConditions.urlContains("/groups"));
        return new GroupsPage(this.driver);
    }

    public UsersPage navigateToUsersPage(){
        pressButtonFromSideMenu(usersLocator);
        this.wait.until(ExpectedConditions.urlContains("/users"));
        return new UsersPage(this.driver);
    }

    public ParishesPage navigateToParishesPage(){
        pressButtonFromSideMenu(parishesLocator);
        this.wait.until(ExpectedConditions.urlContains("/parishes"));
        return new ParishesPage(this.driver);
    }

    public ScanPage navigateToScanPage(){
        pressButtonFromSideMenu(scanTaskLocator);
        this.wait.until(ExpectedConditions.urlContains("/scanTask"));
        return new ScanPage(this.driver);
    }
}
