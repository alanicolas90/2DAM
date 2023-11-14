package dao;

import io.vavr.control.Either;

public interface CredentialsDao {

    Either<Integer,Boolean> usernameExists(String username);
}
