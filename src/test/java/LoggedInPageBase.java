import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class LoggedInPageBase extends PageBase {

    protected By logoutLocator = By.xpath("//button[span[contains(., \"Kijelentkezés\")]]");
    
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
}
