package dao.impl;

import dao.LoginDao;
import model.Credential;

import java.util.List;

public class LoginDaoImpl implements LoginDao {
    
    @Override public boolean login(String username, String password) {
        List<Credential> credentials = List.of(new Credential(0,"root", "2dam",true));

        return credentials.stream().anyMatch(c -> c.getUsername().equals(username) && c.getPassword().equals(password));
    }
}
