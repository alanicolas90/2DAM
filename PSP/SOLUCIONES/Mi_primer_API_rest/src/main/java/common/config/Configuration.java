package common.config;

import common.Constants;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String pathData;
    private String baseURL;
    private String apiKey;

    private Configuration() {
        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader()
                    .getResourceAsStream(Constants.CONFIG_PROPERTIES));
            this.pathData = p.getProperty(Constants.PATH_DATA);
            this.baseURL = p.getProperty(Constants.BASE_URL);
            this.apiKey = p.getProperty(Constants.API_KEY);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}