package config;

import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;


@Log4j2
@Singleton
public class Configuration {
    private Properties p;

    private Configuration() {
        try {
            p = new Properties();
            p.loadFromXML(Configuration.class.getClassLoader().getResourceAsStream("config/properties.xml"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


    public String getProperty(String key) {
        return p.getProperty(key);
    }

}
