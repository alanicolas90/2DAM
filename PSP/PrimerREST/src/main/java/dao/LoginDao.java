package dao;

import dao.model.Credential;
import dao.model.ErrorC;
import io.vavr.control.Either;

public interface LoginDao {
    Either<ErrorC, Credential> get(Credential credential);
}
