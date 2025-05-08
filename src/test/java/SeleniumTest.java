import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {
    
    private WebDriver driver;
    private Map<String, Object> jsonData;

    @Before
    public void setup() throws MalformedURLException, IOException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        assertNotNull(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        jsonData = mapper.readValue(is, Map.class);

    }

    @After
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
