package domain.usecases;

import common.ApiError;
import dao.DaoCountries;
import domain.modelo.Country;
import domain.modelo.CountryInfo;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class CountryInfoUseCase {
    private final DaoCountries daoCountries;

    @Inject
    public CountryInfoUseCase(DaoCountries daoCountries) {
        this.daoCountries = daoCountries;
    }

    public Either<ApiError, CountryInfo> getCountryInfoRetrofitCall(Country country) {
        return daoCountries.getCountryInfoRetrofitCall(country);
    }
}