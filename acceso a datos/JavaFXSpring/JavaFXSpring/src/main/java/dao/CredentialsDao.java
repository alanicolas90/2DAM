package dao;

import io.vavr.control.Either;
import model.ErrorC;

public interface CredentialsDao {

    Either<ErrorC, Boolean> usernameExists(String username);
}
