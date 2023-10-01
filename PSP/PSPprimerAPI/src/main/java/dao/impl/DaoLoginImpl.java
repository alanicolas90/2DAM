package dao.impl;

import common.config.Configuracion;
import dao.DaoLogin;
import domain.modelo.Usuario;
import jakarta.inject.Inject;

public class DaoLoginImpl implements DaoLogin {


    private Configuracion configuracion;

    @Inject
    public DaoLoginImpl(Configuracion configuracion) {
        this.configuracion = configuracion;
    }

    @Override
    public boolean doLogin(Usuario user) {
        return user.nombre().equals("admin") || user.nombre().equals("user");
    }
}
