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
        Either<Integer, Boolean> credential = credentialsDao.usernameExists(username);
        if (credential.isLeft()) {
            return switch (credential.getLeft()) {
                case 1-> Either.left(new ErrorC("Username does not exist"));
                case 2-> Either.left(new ErrorC("Database error"));
                default -> Either.left(new ErrorC("Unknown error"));
            };
        } else {
            return Either.right(credential.get());
        }
    }
}
