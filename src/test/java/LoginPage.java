import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

    //private By titleLocator = By.xpath("//div[span[contains(., \"Bejelentkezés\")]]");

    private By emailInputLocator = By.xpath("//div[label[contains(., \"Email\")]]/input");
    private By passwordInputLocator = By.xpath("//div[label[contains(., \"Jelszó\")]]/input");
    private By loginButtonLocator = By.xpath("//button[span[contains(., \"Bejelentkezés\")]]");
    
    public LoginPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Bejelentkezés\")]]");
        this.driver.get(config.getBaseUrl() + "/login");
        waitAndReturnElement(bodyLocator);
    }

    public void fillEmailInput(String email){
        WebElement emailInputElement = waitAndReturnElement(emailInputLocator);
        emailInputElement.sendKeys(email);
    }

    public void fillPasswordInput(String password){
        WebElement passwordInputElement = waitAndReturnElement(passwordInputLocator);
        passwordInputElement.sendKeys(password);
    }

    public ProfilePage pressLoginAndNavigate(){
        pressButton(loginButtonLocator);
        this.wait.until(ExpectedConditions.urlContains("/profile"));
        return new ProfilePage(this.driver);
    }
}
