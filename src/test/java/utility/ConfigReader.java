package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = "src/test/resources/config.properties";
    private static boolean isLoaded = false;

    // Load only once
    public static void loadProperties() {
        if (!isLoaded) {
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                properties.load(fis);
                isLoaded = true;
            } catch (IOException e) {
                throw new RuntimeException("Failed to load config file at: " + CONFIG_PATH, e);
            }
        }
    }

    // Access any property by key
    public static String getProperty(String key) {
        if (!isLoaded) {
            loadProperties();  // auto-load if not loaded
        }
        return properties.getProperty(key);
    }
}
