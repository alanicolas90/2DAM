package dao;

import common.ApiError;
import domain.modelo.Country;
import domain.modelo.CountryInfo;
import io.vavr.control.Either;

import java.util.List;

public interface DaoCountries {
    Either<ApiError, List<Country>> getCountriesRetrofitCall();

    Either<ApiError, CountryInfo> getCountryInfoRetrofitCall(Country country);
}