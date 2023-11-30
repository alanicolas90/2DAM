package ui.screens.list_countries;

import common.ApiError;
import common.Constants;
import common.NotLoadedError;
import domain.modelo.Country;
import domain.modelo.CountryInfo;
import domain.usecases.CountryInfoUseCase;
import domain.usecases.LoadCountriesUseCase;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;
import java.util.List;

public class ListCountriesViewModel {

    private final LoadCountriesUseCase loadCountriesUseCase;
    private final CountryInfoUseCase countryInfoUseCase;
    private final ObjectProperty<ListCountriesState> state;

    @Inject
    public ListCountriesViewModel(LoadCountriesUseCase loadCountriesUseCase, CountryInfoUseCase countryInfoUseCase) {
        this.loadCountriesUseCase = loadCountriesUseCase;
        this.countryInfoUseCase = countryInfoUseCase;
        state = new SimpleObjectProperty<>(new ListCountriesState(null, null, null, false, null));
    }

    public ReadOnlyObjectProperty<ListCountriesState> getState() {
        return state;
    }

    public void loadCountries() {
        ListCountriesState listCountriesState;
        Either<ApiError, List<Country>> countries = loadCountriesUseCase.getCountriesRetrofitCall();
        if (countries.isLeft())
            listCountriesState = new ListCountriesState(null, null, new NotLoadedError(Constants.THE_COUNTRIES_COULD_NOT_LOAD, LocalDateTime.now()), false, null);
        else
            listCountriesState = new ListCountriesState(countries.get(), null, null, false, null);
        state.setValue(listCountriesState);
    }

    public void selectCountry(Country country) {
        Either<ApiError, CountryInfo> result;
        if (country != null) {
            result = countryInfoUseCase.getCountryInfoRetrofitCall(country);
        } else {
            result = Either.left(new NotLoadedError(Constants.NO_COUNTRY_SELECTED, LocalDateTime.now()));
        }
        result.peek(countryInfo -> state.setValue(new ListCountriesState(state.get().getCountries(), countryInfo, null, false, country)))
                .peekLeft(apiError -> state.setValue(new ListCountriesState(state.get().getCountries(), null, apiError, false, country)));
    }
}