package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
@Getter
@Singleton
public class ConfiguracionProperties {

    private String articleData;
    private String newspaperData;
    private String readerData;

    public ConfiguracionProperties() {

        try {
            Properties p = new Properties();
            p.load(getClass().getClassLoader().getResourceAsStream(StringsUtils.CONFIG_PROPERTIES));
            this.articleData = p.getProperty(StringsUtils.ARTICLE_DATA);
            this.newspaperData = p.getProperty(StringsUtils.NEWSPAPER_DATA);
            this.readerData = p.getProperty(StringsUtils.READER_DATA);

        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }

    }
}
