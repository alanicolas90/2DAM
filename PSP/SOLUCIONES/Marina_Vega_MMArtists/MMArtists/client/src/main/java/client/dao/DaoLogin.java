package client.dao;

import domain.common.ApiError;
import domain.model.User;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface DaoLogin {

    Single<Either<ApiError, String>> login(String username, String password);

    Single<Either<ApiError, User>> register(User user);
}