package dao.impl;

import dao.LoginDao;
import domain.modelo.Credential;

import java.util.List;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean login(String username, String password) {
        List<Credential> credentials = List.of(new Credential(0, "root", "", true));

        return credentials.stream().anyMatch(c -> c.getUsername().equals(username) && c.getPassword().equals(password));
    }
}
