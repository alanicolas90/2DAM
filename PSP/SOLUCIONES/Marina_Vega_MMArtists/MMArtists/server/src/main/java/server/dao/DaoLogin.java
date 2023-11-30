package server.dao;

import domain.model.User;

public interface DaoLogin {
    User userLogged(User user);

    boolean register(User user);

    boolean userNameTaken(User user);
}