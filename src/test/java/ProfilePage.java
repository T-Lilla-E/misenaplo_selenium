import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends LoggedInPageBase {

    //private By titleLocator = By.xpath("//div[span[contains(., \"Felhasználói profil\")]]");

    //private By locatorOfMirjam = By.xpath("//h1[contains(., \"Betlehemi Szent Mirjam\")]");
    
    public ProfilePage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Felhasználói profil\")]]");
        this.driver.get("https://misenaplo.hu/profile");
        waitAndReturnElement(bodyLocator);
    }

    // @Override
    // public String getTitle() {
    //     WebElement titleElement = waitAndReturnElement(titleLocator);
    //     return titleElement.getText();
    // }
}
