import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class SeleniumTest {
    
    private WebDriver driver;
    // private Map<String, Object> jsonData;

    @Before
    public void setup() throws MalformedURLException, IOException, StreamReadException, DatabindException {
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
        LoginPage loginPage = new LoginPage(this.driver);
        Assert.assertTrue(loginPage.getTitle().contains(("Bejelentkezés").toUpperCase()));

        loginPage.fillEmailInput("tle@mailinator.com");
        loginPage.fillPasswordInput("123456");
        ProfilePage profilePage = loginPage.pressLoginAndNavigate();

        Assert.assertTrue(profilePage.getTitle().contains(("Felhasználói profil").toUpperCase()));
    }

    @After
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
