package domain.usecases;

import common.ApiError;
import dao.DaoStates;
import domain.modelo.Country;
import domain.modelo.State;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class LoadStatesUseCase {
    private final DaoStates daoStates;

    @Inject
    public LoadStatesUseCase(DaoStates daoStates) {
        this.daoStates = daoStates;
    }

    public Either<ApiError, List<State>> getStatesRetrofitCall(Country country) {
        return daoStates.getStatesRetrofitCall(country);
    }
}