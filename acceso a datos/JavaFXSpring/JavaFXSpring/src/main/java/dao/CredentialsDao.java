package dao;

import io.vavr.control.Either;
import model.ErrorC;

public interface CredentialsDao {

    Either<Integer,Boolean> usernameExists(String username);
}
