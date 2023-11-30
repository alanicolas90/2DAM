package client.dao.impl;

import client.dao.common.DaoConstants;
import com.google.gson.Gson;
import domain.common.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import okhttp3.MediaType;
import retrofit2.HttpException;
import retrofit2.Response;

import java.util.Objects;

abstract class DaoGenerics {

    private final Gson gson;

    @Inject
    protected DaoGenerics(Gson gson) {
        this.gson = gson;
    }

    public <T> Single<Either<ApiError, T>> safeSingleApiCall(Single<T> call) {
        return call.map(t -> Either.right(t).mapLeft(o -> (ApiError) o))
                .subscribeOn(Schedulers.io())
                .onErrorReturn(throwable -> {
                    Either<ApiError, T> error = Either.left(new ApiError(DaoConstants.NETWORK_ERROR));
                    if (throwable instanceof HttpException httpException) {
                        Response<?> response = httpException.response();
                        if (response != null && response.errorBody() != null) {
                            if (Objects.equals(response.errorBody().contentType(), MediaType.get(DaoConstants.CONTENT_TYPE_JSON))) {
                                ApiError apiError = gson.fromJson(response.errorBody().charStream(), ApiError.class);
                                error = Either.left(apiError);
                            } else {
                                error = Either.left(new ApiError(response.message()));
                            }
                        }
                    }
                    return error;
                });


    }

    public Single<Either<ApiError, String>> safeSingleVoidApiCall(Single<Response<Void>> call) {
        return call.map(response -> {
                    Either<ApiError, String> answer = Either.right(DaoConstants.OK);
                    if (!response.isSuccessful()) {
                        answer = Either.left(new ApiError(response.message()));
                        if (response.errorBody() != null && (Objects.equals(response.errorBody().contentType(), MediaType.get(DaoConstants.CONTENT_TYPE_JSON)))) {
                            ApiError apiError = gson.fromJson(response.errorBody().charStream(), ApiError.class);
                            answer = Either.left(apiError);
                        }
                    }
                    return answer;
                })
                .subscribeOn(Schedulers.io());
    }

}