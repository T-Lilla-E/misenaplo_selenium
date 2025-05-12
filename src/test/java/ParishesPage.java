import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParishesPage extends LoggedInPageBase {
    
    public ParishesPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Plébániák\")]]");
        this.driver.get("https://misenaplo.hu/parishes");
        waitAndReturnElement(bodyLocator);
    }
}
