package server.dao.impl;


import domain.model.User;
import server.dao.DaoLogin;
import server.dao.data.StaticLists;

public class DaoLoginImpl implements DaoLogin {

    @Override
    public User userLogged(User user) {
        return StaticLists.userList.stream().filter(user1 -> user1.getUserName().equals(user.getUserName())).findFirst().orElse(null);
    }

    @Override
    public boolean register(User user) {
        return StaticLists.userList.add(user);
    }

    @Override
    public boolean userNameTaken(User user) {
        return StaticLists.userList.contains(user);
    }
}