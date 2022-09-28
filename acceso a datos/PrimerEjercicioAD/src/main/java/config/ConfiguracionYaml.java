package config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.inject.Singleton;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Getter
@Log4j2
@Singleton
public class ConfiguracionYaml {

    private static ConfiguracionYaml configuracionYaml;


    public static synchronized ConfiguracionYaml getInstance(){
        if (configuracionYaml == null)
        {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();

            try {
                configuracionYaml = mapper.readValue(
                        ConfiguracionYaml.class.getClassLoader().getResourceAsStream(StringsUtils.CONFIG_YAML),
                        ConfiguracionYaml.class);


            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        return configuracionYaml;
    }
    //METER LO QUE CONTIENE EL YAML
    private String articleData;

}
