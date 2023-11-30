package client.dao.impl;

import client.dao.DaoLogin;
import client.dao.retrofit.llamadas.LoginApi;
import com.google.gson.Gson;
import domain.common.ApiError;
import domain.model.User;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DaoLoginImpl extends DaoGenerics implements DaoLogin {

    private final LoginApi loginApi;

    @Inject
    public DaoLoginImpl(LoginApi loginApi, Gson gson) {
        super(gson);
        this.loginApi = loginApi;
    }

    @Override
    public Single<Either<ApiError, String>> login(String username, String password) {
        return safeSingleVoidApiCall(loginApi.getLogin(username, password));
    }

    @Override
    public Single<Either<ApiError, User>> register(User user) {
        return safeSingleApiCall(loginApi.register(user));
    }
}