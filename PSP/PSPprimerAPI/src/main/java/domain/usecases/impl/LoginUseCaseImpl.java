package domain.usecases.impl;

import dao.DaoLogin;
import domain.modelo.Usuario;
import domain.usecases.LoginUseCase;
import jakarta.inject.Inject;


public class LoginUseCaseImpl implements LoginUseCase {


    private DaoLogin daoLogin;

    @Inject
    public LoginUseCaseImpl(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }


    @Override
    public boolean doLogin(Usuario usuario) {

        return daoLogin.doLogin(usuario);

    }
}
