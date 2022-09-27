package dao.impl;

import config.ConfiguracionYaml;
import dao.DaoLogin;
import domain.modelo.Reader;
import jakarta.inject.Inject;

public class DaoLoginImpl implements DaoLogin {


    private ConfiguracionYaml configuracionYaml;

    @Inject
    public DaoLoginImpl(ConfiguracionYaml configuracionYaml) {
        this.configuracionYaml = configuracionYaml;
    }

    @Override
    public boolean doLogin(Reader user) {

        // buscar usuario
        if (user.nombre().equals("admin") || user.nombre().equals("user"))
            return true;
        return false;
    }
}
