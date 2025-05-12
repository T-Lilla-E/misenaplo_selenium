import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UsersPage extends LoggedInPageBase {
    
    public UsersPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Felhasználók\")]]");
        this.driver.get("https://misenaplo.hu/users");
        waitAndReturnElement(bodyLocator);
    }
}
