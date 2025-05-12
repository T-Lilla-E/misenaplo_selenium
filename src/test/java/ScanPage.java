import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ScanPage extends LoggedInPageBase {
    
    public ScanPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Csoportba rögzítés\")]]");
        this.driver.get("https://misenaplo.hu/scantTask");
        waitAndReturnElement(bodyLocator);
    }
}
