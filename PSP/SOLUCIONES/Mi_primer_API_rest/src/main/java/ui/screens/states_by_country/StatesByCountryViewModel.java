package ui.screens.states_by_country;

import common.ApiError;
import common.Constants;
import common.NotLoadedError;
import domain.modelo.Country;
import domain.modelo.State;
import domain.usecases.LoadCountriesUseCase;
import domain.usecases.LoadStatesUseCase;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.List;

public class StatesByCountryViewModel {

    private final LoadStatesUseCase loadStatesUseCase;
    private final LoadCountriesUseCase loadCountriesUseCase;
    private final ObjectProperty<StatesByCountryState> state;

    @Inject
    public StatesByCountryViewModel(LoadStatesUseCase loadStatesUseCase, LoadCountriesUseCase loadCountriesUseCase) {
        this.loadStatesUseCase = loadStatesUseCase;
        this.loadCountriesUseCase = loadCountriesUseCase;
        state = new SimpleObjectProperty<>(new StatesByCountryState(null, null, null, false, null));
    }

    public ReadOnlyObjectProperty<StatesByCountryState> getState() {
        return state;
    }

    public void loadCountries() {
        StatesByCountryState statesByCountryState;
        Either<ApiError, List<Country>> countries = loadCountriesUseCase.getCountriesRetrofitCall();
        if (countries.isLeft())
            statesByCountryState = new StatesByCountryState(null, null, new NotLoadedError(Constants.THE_STATES_COULD_NOT_LOAD, LocalDateTime.now()), false, null);
        else
            statesByCountryState = new StatesByCountryState(null, countries.get(), null, false, null);
        state.setValue(statesByCountryState);
    }

    public void selectCountry(Country country) {
        Either<ApiError, List<State>> result;
        if (country != null) {
            result = loadStatesUseCase.getStatesRetrofitCall(country);
        } else {
            result = Either.left(new NotLoadedError(Constants.NO_COUNTRY_SELECTED, LocalDateTime.now()));
        }
        result.peek(stateList -> state.setValue(new StatesByCountryState(stateList, state.get().getCountries(), null, false, country)))
                .peekLeft(apiError -> state.setValue(new StatesByCountryState(null, state.get().getCountries(), apiError, false, country)));
    }
}