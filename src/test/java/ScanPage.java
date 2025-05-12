import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScanPage extends LoggedInPageBase {

    private By headerLocator = By.tagName("header");
    private By fieldsetLocator = By.tagName("fieldset");
    private By imageLocator = By.xpath("//div[contains(@class, \"image--cover\")]");
    private By footerLocator = By.tagName("footer");

    
    public ScanPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Csoportba rögzítés\")]]");
        this.driver.get(config.getBaseUrl() + "/scanTask");
        waitAndReturnElement(bodyLocator);
    }

    public boolean hasHeader(){
        WebElement header = waitAndReturnElement(headerLocator);
        return header.isDisplayed();
    }

    public boolean hasDropdown(){
        WebElement dropdown = waitAndReturnElement(fieldsetLocator);
        return dropdown.isDisplayed();
    }

    public boolean hasImage(){
        WebElement image = waitAndReturnElement(imageLocator);
        return image.isDisplayed();
    }

    public boolean hasFooter(){
        WebElement footer = waitAndReturnElement(footerLocator);
        return footer.isDisplayed();
    }
}
