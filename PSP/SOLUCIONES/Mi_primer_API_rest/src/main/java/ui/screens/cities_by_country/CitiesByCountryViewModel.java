package ui.screens.cities_by_country;

import common.ApiError;
import common.Constants;
import common.NotLoadedError;
import domain.modelo.City;
import domain.modelo.Country;
import domain.usecases.LoadCitiesUseCase;
import domain.usecases.LoadCountriesUseCase;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.List;

public class CitiesByCountryViewModel {

    private final LoadCitiesUseCase loadCitiesUseCase;
    private final LoadCountriesUseCase loadCountriesUseCase;
    private final ObjectProperty<CitiesByCountryState> state;

    @Inject
    public CitiesByCountryViewModel(LoadCitiesUseCase loadCitiesUseCase, LoadCountriesUseCase loadCountriesUseCase) {
        this.loadCitiesUseCase = loadCitiesUseCase;
        this.loadCountriesUseCase = loadCountriesUseCase;
        state = new SimpleObjectProperty<>(new CitiesByCountryState(null, null, null, false, null));
    }

    public ReadOnlyObjectProperty<CitiesByCountryState> getState() {
        return state;
    }

    public void loadCountries() {
        CitiesByCountryState citiesByCountryState;
        Either<ApiError, List<Country>> countries = loadCountriesUseCase.getCountriesRetrofitCall();
        if (countries.isLeft())
            citiesByCountryState = new CitiesByCountryState(null, null, new NotLoadedError(Constants.THE_CITIES_COULD_NOT_LOAD, LocalDateTime.now()), false, null);
        else
            citiesByCountryState = new CitiesByCountryState(null, countries.get(), null, false, null);
        state.setValue(citiesByCountryState);
    }

    public void selectCountry(Country country) {
        Either<ApiError, List<City>> result;
        if (country != null) {
            result = loadCitiesUseCase.getCitiesRetrofitCall(country);
        } else {
            result = Either.left(new NotLoadedError(Constants.NO_COUNTRY_SELECTED, LocalDateTime.now()));
        }
        result.peek(cityList -> state.setValue(new CitiesByCountryState(cityList, state.get().getCountries(), null, false, country)))
                .peekLeft(apiError -> state.setValue(new CitiesByCountryState(null, state.get().getCountries(), apiError, false, country)));
    }
}