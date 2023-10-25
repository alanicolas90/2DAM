package common.config;


import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String pathDatos;

    public Configuration() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream("config.properties"));
            this.pathDatos = p.getProperty("urlBase");

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


}
