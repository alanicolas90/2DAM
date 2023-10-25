package dao;

import io.vavr.control.Either;
import model.Credential;
import model.ErrorC;

public interface LoginDao {
    Either<ErrorC, Credential> get(String username, String password);
}
