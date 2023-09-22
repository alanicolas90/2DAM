package service;

import dao.LoginDao;
import jakarta.inject.Inject;

public class LoginService {

    @Inject
    private LoginDao dao;

public boolean login(String username, String password) {
        return dao.login(username, password);
    }
}

