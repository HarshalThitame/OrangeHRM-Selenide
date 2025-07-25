package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static boolean isLoaded = false;
    // Load only once

    public static void loadProperties(String env) {
        String CONFIG_PATH = "src/test/resources/config/" + env + ".properties";
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
        return properties.getProperty(key);
    }
}
