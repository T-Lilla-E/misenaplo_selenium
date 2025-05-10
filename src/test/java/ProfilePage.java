import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends LoggedInPageBase {

    private By titleLocator = By.xpath("//div[span[contains(., \"Felhasználói profil\")]]");

    private By locatorOfMirjam = By.xpath("//h1[contains(., \"Betlehemi Szent Mirjam\")]");
    
    public ProfilePage(WebDriver driver){
        super(driver);
        this.driver.get("https://misenaplo.hu/profile");
        waitAndReturnElement(bodyLocator);
    }

    public String getTitle() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }

    public LandingPage pressLogoutAndNavigate(){
        pressButtonFromSideMenu(logoutLocator);
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locatorOfMirjam));
        //this.wait.until(ExpectedConditions.urlToBe("https://misenaplo.hu"));
        return new LandingPage(this.driver);
    }
}
