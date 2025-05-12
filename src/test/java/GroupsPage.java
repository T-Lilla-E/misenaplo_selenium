import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupsPage extends LoggedInPageBase {
    
    public GroupsPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Csoportok\")]]");
        this.driver.get("https://misenaplo.hu/groups");
        waitAndReturnElement(bodyLocator);
    }
}
