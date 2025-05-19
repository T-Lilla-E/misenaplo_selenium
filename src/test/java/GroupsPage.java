import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GroupsPage extends LoggedInPageBase {

    private By checkboxLocator = By.xpath("//label[contains(., \"Csak a saját csoportjaim\")]");
    private By addButtonLocator = By.xpath("//div[contains(@class, \"row\")]//button[not(contains(@disabled, \"disabled\"))]");
    private By dialogTitleLocator = By.xpath("//span[contains(., \"Új csoport\")]");
    private By nameInputLocator = By.xpath("//div[.//label[contains(., \"Név\")]]/input");
    private By saveButtonLocator = By.xpath("//button[.//span[contains(., \"Hozzáadás\")]]");
    private By groupNameLocator = By.xpath("//td[contains(., \"" + config.getGroupName() + "\")]");
    
    public GroupsPage(WebDriver driver){
        super(driver);
        this.titleLocator = By.xpath("//div[span[contains(., \"Csoportok\")]]");
        this.driver.get(config.getBaseUrl() + "/groups");
        waitAndReturnElement(bodyLocator);
    }

    public void checkCheckbox(){
        WebElement checkboxElement = waitAndReturnElement(checkboxLocator);
        if(!checkboxElement.isSelected()){
            pressButton(checkboxLocator);
        }
    }

    public void clickOnAddButton(){
        waitAndReturnElement(addButtonLocator);
        pressButton(addButtonLocator);
    }

    public String getDialogTitle(){
        WebElement title = waitAndReturnElement(dialogTitleLocator);
        return title.getText();
    }

    public void fillNameInput(){
        WebElement inputElement = waitAndReturnElement(nameInputLocator);
        inputElement.sendKeys(config.getGroupName());
    }

    public void saveGroup(){
        waitAndReturnElement(saveButtonLocator);
        pressButton(saveButtonLocator);
    }

    public boolean isGroupNameInList(){
        boolean shown;
        try {
            waitAndReturnElement(groupNameLocator);
            shown = true;
        }
        catch(Exception e){
            shown = false;
        }
        return shown;
    }
}
