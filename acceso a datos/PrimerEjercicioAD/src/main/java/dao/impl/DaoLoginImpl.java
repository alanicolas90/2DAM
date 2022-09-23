package dao.impl;

import common.config.Configuracion;
import dao.DaoLogin;
import domain.modelo.Reader;
import jakarta.inject.Inject;

public class DaoLoginImpl implements DaoLogin {


    private Configuracion configuracion;

    @Inject
    public DaoLoginImpl(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public boolean doLogin(Reader user) {

        // buscar usuario
        if (user.getNombre().equals("admin") || user.getNombre().equals("user"))
            return true;
        return false;
    }
}
