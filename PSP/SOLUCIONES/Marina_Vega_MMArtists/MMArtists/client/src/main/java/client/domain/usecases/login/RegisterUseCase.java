package client.domain.usecases.login;

import client.dao.DaoLogin;
import domain.common.ApiError;
import domain.common.DomainConstants;
import domain.model.User;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class RegisterUseCase {

    private final DaoLogin daoLogin;

    @Inject
    public RegisterUseCase(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Single<Either<ApiError, User>> register(User user) {
        return Single.zip(validateUserName(user),
                        validatePassword(user),
                daoLogin.register(user),
                (userNameValidation, passwordValidation, register) ->
                    userNameValidation
                            .flatMap(x -> passwordValidation)
                            .flatMap(x -> register)
                );
    }

    private Single<Either<ApiError, Object>> validateUserName(User user) {
        if (user.getUserName() == null || user.getUserName().length() < 3) {
            return Single.just(Either.left(new ApiError(DomainConstants.THE_USERNAME_HAS_TO_BE_AT_LEAST_3_CHARACTERS_LONG)));
        }
        return Single.just(Either.right(null));
    }

    private Single<Either<ApiError, Object>> validatePassword(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return Single.just(Either.left(new ApiError(DomainConstants.THE_PASSWORD_IS_NOT_VALID)));
        }
        return Single.just(Either.right(null));
    }
}
