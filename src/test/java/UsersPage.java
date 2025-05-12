import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage extends LoggedInPageBase {
    
    public UsersPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Felhasználók\")]]");
        this.driver.get(config.getBaseUrl() + "/users");
        waitAndReturnElement(bodyLocator);
    }
}
