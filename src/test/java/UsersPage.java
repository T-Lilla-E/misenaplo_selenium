import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsersPage extends LoggedInPageBase {

    private By addButtonLocator = By.xpath("//div[contains(@class, \"row\")]//button[not(contains(@disabled, \"disabled\"))]");
    private By tooltipLocator = By.xpath("//div[contains(@class, \"v-tooltip\") and contains(., \"Hozzáadás\")]");
    
    public UsersPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Felhasználók\")]]");
        this.driver.get(config.getBaseUrl() + "/users");
        waitAndReturnElement(bodyLocator);
    }

    public boolean hoverOnAddButtonShowsTooltip(){
        hoverOnElement(addButtonLocator);
        WebElement tooltip = waitAndReturnElement(tooltipLocator);
        return tooltip.isDisplayed();
    }
}
