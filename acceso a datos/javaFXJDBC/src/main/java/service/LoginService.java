package service;

import dao.LoginDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.ErrorC;

public class LoginService {
    private final LoginDao dao;

    @Inject
    public LoginService(LoginDao dao) {
        this.dao = dao;
    }

    public Either<ErrorC, Credential> login(String username, String password) {
        return dao.get(username, password);
    }
}

