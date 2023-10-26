package service;

import dao.LoginDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.ErrorC;

public class LoginService {
    private final LoginDao daoLogin;

    @Inject
    public LoginService(LoginDao daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Either<ErrorC, Credential> get(Credential credential) {
        return daoLogin.get(credential);
    }
}

