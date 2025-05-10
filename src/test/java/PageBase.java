import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By bodyLocator = By.tagName("body");
    protected By navLocator = By.tagName("nav");

    private String[] urls;

    public PageBase(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public PageBase(WebDriver driver, String[] urls){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.urls = urls;
    }

    protected WebElement waitAndReturnElement(By locator){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public void pressButton(By locator){
        WebElement buttonElement = waitAndReturnElement(locator);
        buttonElement.click();
    }

    public void pressButtonFromSideMenu(By locator){
        WebElement sidenavMenu = waitAndReturnElement(navLocator);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(sidenavMenu).perform();
        WebElement buttonElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        actions.moveToElement(buttonElement).click().perform();
    }
}