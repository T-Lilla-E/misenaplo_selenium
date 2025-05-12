import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends PageBase {

    public final String[] locatorsOfLandingPage = {
        "//div[contains(@style, \"Mirjam\")]", // Mirjam image
        "//h1[contains(., \"MisEnapló\")]", // application title
        "//button[span[contains(., \"Bejelentkezés\")]]", // login button
        "//p[contains(., \"Vörös László\")]", // developer name
        "//div[contains(@style, \"ikon\")]" // icon 
    };
    
    public LandingPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Kezdőlap\")]]");
        this.driver.get("https://misenaplo.hu");
        waitAndReturnElement(bodyLocator);
    }

    public boolean elementDisplayed(String locatorString){
        By locator = By.xpath(locatorString);
        WebElement element = waitAndReturnElement(locator);
        return element.isDisplayed();
    }
}
