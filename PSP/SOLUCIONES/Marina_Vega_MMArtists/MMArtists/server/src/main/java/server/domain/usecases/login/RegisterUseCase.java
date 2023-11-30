package server.domain.usecases.login;

import domain.model.User;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import server.dao.DaoLogin;
import server.domain.common.DomainConstants;
import server.domain.errors.NotValidFieldException;

public class RegisterUseCase {
    private final DaoLogin daoLogin;
    private final Pbkdf2PasswordHash passwordHash;


    @Inject
    public RegisterUseCase(DaoLogin daoLogin, Pbkdf2PasswordHash passwordHash) {
        this.daoLogin = daoLogin;
        this.passwordHash = passwordHash;
    }

    public boolean doRegister(User user) {
        if (user.getUserName() == null || user.getUserName().length() < 3) {
            throw new NotValidFieldException(domain.common.DomainConstants.THE_USERNAME_HAS_TO_BE_AT_LEAST_3_CHARACTERS_LONG);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new NotValidFieldException(domain.common.DomainConstants.THE_PASSWORD_IS_NOT_VALID);
        }
        if (daoLogin.userNameTaken(user)) {
            throw new NotValidFieldException(DomainConstants.THE_USERNAME_IS_ALREADY_TAKEN);
        }
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        return daoLogin.register(user);
    }
}