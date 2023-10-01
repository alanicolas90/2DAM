package common.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import common.StringsUtils;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
@Singleton
public class Configuracion {

    private Configuracion() {
    }

    private static Configuracion configuracion;
    private String pathDatos;


    public static synchronized Configuracion getInstance() {
        if (configuracion == null) {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            try {
                mapper.readValue(
                        Configuracion.class.getClassLoader().getResourceAsStream(StringsUtils.CONFIG_YAML),
                        Configuracion.class);


            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return configuracion;

    }
    //METER LO QUE CONTIENE EL YAML

}
