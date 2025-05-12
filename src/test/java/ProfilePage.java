import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends LoggedInPageBase {
    
    public ProfilePage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Felhasználói profil\")]]");
        this.driver.get("https://misenaplo.hu/profile");
        waitAndReturnElement(bodyLocator);
    }
}
