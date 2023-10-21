package dao;

import io.vavr.control.Either;
import model.Credential;
import model.ErrorC;

public interface LoginDao {
    Either<ErrorC, Credential> login(String username, String password);
}
