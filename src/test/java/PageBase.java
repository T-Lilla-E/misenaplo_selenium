import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBase {
    
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ConfigReader config;

    protected By bodyLocator = By.tagName("body");
    protected By navLocator = By.tagName("nav");
    protected By headTitleLocator = By.xpath("//title");

    protected By titleLocator;

    public PageBase(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.config = new ConfigReader();
    }

    public WebElement waitAndReturnElement(By locator){
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    protected String getTitle(){
        WebElement titleElement = waitAndReturnElement(titleLocator);
        return titleElement.getText();
    }

    protected String getTitle2(){
        WebElement headTitleElement = waitAndReturnElement(headTitleLocator);
        return headTitleElement.getText();
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

    public void hoverOnElement(By locator){
        WebElement element = waitAndReturnElement(locator);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).perform();
    }
}