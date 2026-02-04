package uiApi.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

    private static Properties prop;
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);

    /**
     * Initialize properties from config file
     */
    public static Properties initProperties() {
        prop = new Properties();
        String configPath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

        try (FileInputStream fis = new FileInputStream(configPath)) {
            prop.load(fis);
            logger.info("Config properties loaded successfully from {}", configPath);
        } catch (IOException e) {
            logger.error("Failed to load config.properties from path: {}", configPath, e);
        }
        return prop;
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        if (prop == null) {
            logger.warn("Properties not initialized yet. Calling initProperties()");
            initProperties();
        }

        String value = prop.getProperty(key);
        if (value == null) {
            logger.warn("Property '{}' not found in config file", key);
        } else {
            logger.debug("Retrieved property '{}' with value '{}'", key, value);
        }
        return value;
    }
}