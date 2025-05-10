import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {
    
    private WebDriver driver;
    // private Map<String, Object> jsonData;

    private final String[] urls = {"/profile", "/groups", "/users", "/parishes", "/scanTask"};
    private final String[] classNames = {"ProfilePage", "GroupsPage", "UsersPage", "ParishesPage", "ScanPage"};
    private final String[] pageTitles = {"Felhasználói profil", "Csoportok", "Felhasználók", "Plébániák", "Csoportba rögzítés"}; 

    private ProfilePage fillLoginFormAndNavigate(){
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.fillEmailInput("tle@mailinator.com");
        loginPage.fillPasswordInput("123456");
        ProfilePage profilePage = loginPage.pressLoginAndNavigate();
        return profilePage;
    }

    @BeforeMethod
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();

        // InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        // assertNotNull(inputStream);
        // ObjectMapper mapper = new ObjectMapper();
        // jsonData = mapper.readValue(inputStream, Map.class);
        // System.out.println(jsonData.values());

    }

    @Test
    public void SuccessfulLogin(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        Assert.assertTrue(profilePage.getTitle().contains(("Felhasználói profil").toUpperCase()));
    }

    @Test(dependsOnMethods = {"SuccessfulLogin"})
    public void Logout(){
        ProfilePage profilePage = fillLoginFormAndNavigate();
        LandingPage landingPage = profilePage.pressLogoutAndNavigate();
        Assert.assertTrue(landingPage.getTitle().contains(("Kezdőlap").toUpperCase()));
    }

    // @Test(dependsOnMethods = {"SuccessfulLogin"})
    // public void StaticTestOfLoggedInPages(){
    //     fillLoginFormAndNavigate();
    //     for (int i =0; i< classNames.length; i++) {
    //         try{
    //             Class<?> myClass = Class.forName(classNames[i]);
    //             Constructor <?> myConstructor = myClass.getConstructor(WebDriver.class);
    //             Object instance = myConstructor.newInstance(this.driver);

    //             LoggedInPageBase page = (LoggedInPageBase) instance;
    //             Assert.assertTrue(page.getTitle().contains((pageTitles[i]).toUpperCase()));
    //         }
    //         catch(Exception e){
    //             System.out.println(i);
    //             e.printStackTrace();
    //         }
    //     }
    // }

    @AfterMethod
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
