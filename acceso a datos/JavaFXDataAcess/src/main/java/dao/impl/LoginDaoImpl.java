package dao.impl;

import dao.LoginDao;

public class LoginDaoImpl implements LoginDao {
    
    @Override public boolean login(String username, String password) {
        return username.equals("root") && password.equals("2dam");
    }
}
