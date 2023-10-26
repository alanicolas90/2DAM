package service;

import dao.CredentialsDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.ErrorC;

public class CredentialService {

    private final CredentialsDao credentialsDao;

    @Inject
    public CredentialService(CredentialsDao credentialsDao) {
        this.credentialsDao = credentialsDao;
    }


    public Either<ErrorC, Boolean> usernameExists(String username) {
        return credentialsDao.usernameExists(username);
    }
}
