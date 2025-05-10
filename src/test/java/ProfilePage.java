import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends LoggedInPageBase {

    private By titleLocator = By.xpath("//div[span[contains(., \"Felhasználói profil\")]]");

    
    public ProfilePage(WebDriver driver){
        super(driver);
        this.driver.get("https://misenaplo.hu/profile");
        waitAndReturnElement(bodyLocator);
    }

    public String getTitle() {
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }
}
