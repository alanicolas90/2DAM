package server.domain.usecases.login;

import domain.model.User;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import server.dao.DaoLogin;


public class LoginUseCase {
    private final DaoLogin daoLoginImpl;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public LoginUseCase(DaoLogin daoLoginImpl, Pbkdf2PasswordHash passwordHash) {
        this.daoLoginImpl = daoLoginImpl;
        this.passwordHash = passwordHash;
    }

    public boolean doLogin(User user) {
        User userFromDB = daoLoginImpl.userLogged(user);
        if (userFromDB != null) {
            return passwordHash.verify(user.getPassword().toCharArray(), userFromDB.getPassword());
        }
        return false;
    }
}