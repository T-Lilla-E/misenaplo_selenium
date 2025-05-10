import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SeleniumTest {
    
    private WebDriver driver;
    private Map<String, Object> jsonData;

    @Before
    public void setup() throws MalformedURLException, IOException, StreamReadException, DatabindException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.json");
        assertNotNull(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        jsonData = mapper.readValue(inputStream, Map.class);
        System.out.println(jsonData.values());

    }

    @After
    public void close() {
        if(this.driver != null){
            this.driver.quit();
        }
    }
}
