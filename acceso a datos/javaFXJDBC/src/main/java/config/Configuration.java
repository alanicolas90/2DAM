package config;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Singleton
@Log4j2
public class Configuration {

    private static Configuration instance = null;
    private Properties p;

    private Configuration() {
        try {
            p = new Properties();
            p.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream("config/properties.xml"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static Configuration getInstance() {

        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key) {
        return p.getProperty(key);
    }

}
