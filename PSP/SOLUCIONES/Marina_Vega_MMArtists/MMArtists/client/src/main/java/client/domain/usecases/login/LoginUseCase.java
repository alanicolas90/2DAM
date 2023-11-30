package client.domain.usecases.login;

import client.dao.DaoLogin;
import domain.common.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class LoginUseCase {
    private final DaoLogin daoLogin;

    @Inject
    public LoginUseCase(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Single<Either<ApiError, String>> login(String username, String password) {
        return daoLogin.login(username, password);
    }
}