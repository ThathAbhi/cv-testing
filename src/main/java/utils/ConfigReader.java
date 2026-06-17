package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        try {

            FileInputStream file =
                    new FileInputStream(
                            "src/test/resources/config.properties");

            properties = new Properties();

            properties.load(file);

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to load config.properties from src/test/resources. "
                            + "Make sure the file exists at that path.",
                    e);
        }
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null) {

            throw new RuntimeException(
                    "Missing property '" + key + "' in config.properties");
        }

        return value;
    }
}