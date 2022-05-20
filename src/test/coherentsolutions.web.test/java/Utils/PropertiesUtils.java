package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

    private InputStream input;
    private Properties properties;

    public PropertiesUtils() throws FileNotFoundException {
        input = new FileInputStream("src/main/resources/config.properties");
        properties = new Properties();
    }

    public String getProperty(String propertyName) throws IOException {
        properties.load(input);
        return properties.getProperty(propertyName);
    }
}
