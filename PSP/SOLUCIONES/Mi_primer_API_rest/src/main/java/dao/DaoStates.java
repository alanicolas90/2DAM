package dao;

import common.ApiError;
import domain.modelo.Country;
import domain.modelo.State;
import io.vavr.control.Either;

import java.util.List;

public interface DaoStates {
    Either<ApiError, List<State>> getStatesRetrofitCall(Country country);
}