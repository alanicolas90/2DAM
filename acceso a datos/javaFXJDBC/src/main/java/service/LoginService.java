package service;

import dao.LoginDao;
import jakarta.inject.Inject;
import model.Credential;

public class LoginService {
    private final LoginDao dao;

    @Inject
    public LoginService(LoginDao dao) {
        this.dao = dao;
    }

    public Credential login(String username, String password) {
        if (dao.login(username, password).isLeft()) {
            return null;
        } else {
            return dao.login(username, password).get();
        }
    }
}

