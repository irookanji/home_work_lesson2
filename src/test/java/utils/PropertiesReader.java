package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String CREDENTIALS_PROPERTIES = "credentials.properties";

    private static Properties loadResources(String resources) {
        Properties prop = new Properties();
        try {
            prop.load(PropertiesReader.class.getClassLoader().getResourceAsStream(resources));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getCreds(String key) {
        return loadResources(CREDENTIALS_PROPERTIES).getProperty(key);
    }
}