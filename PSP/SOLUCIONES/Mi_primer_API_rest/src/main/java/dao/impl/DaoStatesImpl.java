package dao.impl;

import common.ApiError;
import common.NotLoadedError;
import dao.DaoStates;
import dao.retrofit.llamadas.StateApi;
import dao.retrofit.modelo.ResponseStateItem;
import domain.modelo.Country;
import domain.modelo.State;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class DaoStatesImpl implements DaoStates {

    private final StateApi stateApi;

    @Inject
    public DaoStatesImpl(StateApi stateApi) {
        this.stateApi = stateApi;
    }

    public Either<ApiError, List<State>> getStatesRetrofitCall(Country country) {
        Either<ApiError, List<State>> response = null;
        Response<List<ResponseStateItem>> statesResponseList;
        List<State> stateList;
        try {
            String ciso = country.getIso2();
            statesResponseList = stateApi.getStatesByCountry(ciso).execute();
            if (statesResponseList.isSuccessful()) {
                assert statesResponseList.body() != null;
                List<ResponseStateItem> responseStates = statesResponseList.body();
                stateList = responseStates.stream()
                        .map(responseStateItem -> new State(responseStateItem.getId(), responseStateItem.getName(), responseStateItem.getIso2())).toList();
                response = Either.right(stateList);
            } else {
                assert statesResponseList.errorBody() != null;
                statesResponseList.errorBody().string();
                response = Either.left(new NotLoadedError(statesResponseList.message(), LocalDateTime.now()));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
}