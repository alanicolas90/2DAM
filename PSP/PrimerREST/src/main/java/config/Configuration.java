package config;

import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

@Getter
@Log4j2
@Singleton
public class Configuration {

    private String ruta;
    private String user;
    private String password;
    private String driver;

    void load(InputStream file) {
        try {
            Yaml yaml = new Yaml();
            Map<String, String> m = yaml.load(file);

            this.ruta = m.get(ConstantesConfig.RUTA);
            this.password = m.get(ConstantesConfig.PASSWORD);
            this.user = m.get(ConstantesConfig.USER);
            this.driver = m.get(ConstantesConfig.DRIVER);


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}


