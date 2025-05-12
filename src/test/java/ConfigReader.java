import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
        private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            if(input != null){
                properties.load(input);
            }
            else {
                System.out.println("No config file!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}
