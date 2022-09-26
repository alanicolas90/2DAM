package common.config;


import io.github.palexdev.materialfx.utils.StringUtils;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Getter
@Log4j2
@Singleton
public class Configuracion {

    private String pathDatos;

    public Configuracion() {

        try {
            Yaml yaml = new Yaml();

            Iterable<Object> it = null;

            it = yaml.loadAll(new FileInputStream("src/main/resources/config.yaml"));
            Map<String, String> map = (Map<String, String>) it.iterator().next();

            this.setPathDatos(map.get("pathDatos"));

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void setPathDatos(String pathDatos) {
        this.pathDatos = pathDatos;
    }


}
