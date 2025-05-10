import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends PageBase {

    //private By titleLocator = By.xpath("//div[span[contains(., \"Kezdőlap\")]]");
    
    public LandingPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Kezdőlap\")]]");
        this.driver.get("https://misenaplo.hu");
        waitAndReturnElement(bodyLocator);
    }

    // public String getTitle(){
    //     WebElement titleElement = waitAndReturnElement(titleLocator);
    //     return titleElement.getText();
    // }
}
