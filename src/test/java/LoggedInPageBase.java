import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoggedInPageBase extends PageBase {

    protected By logoutLocator = By.xpath("//button[span[contains(., \"Kijelentkezés\")]]");
    
    public LoggedInPageBase(WebDriver driver){
        super(driver);
    }

    // public LandingPage pressLogoutAndNavigate(){
    //     pressButton(logoutLocator);
    //     this.wait.until(ExpectedConditions.urlToBe("https://misenaplo.hu"));
    //     return new LandingPage(this.driver);
    // }
}
