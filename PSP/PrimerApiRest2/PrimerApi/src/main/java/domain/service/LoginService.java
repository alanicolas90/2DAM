package domain.service;

import dao.LoginDao;
import jakarta.inject.Inject;

public class LoginService {
    private final LoginDao dao;

    @Inject
    public LoginService(LoginDao dao) {
        this.dao = dao;
    }

    public boolean login(String username, String password) {
        return dao.login(username, password);
    }
}

