package client.common.config;

import client.common.Constants;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String baseURL;

    private Configuration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream(Constants.CONFIG_PROPERTIES));
            this.baseURL = p.getProperty(Constants.BASE_URL);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}