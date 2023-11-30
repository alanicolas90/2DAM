package dao.impl;

import common.ApiError;
import common.NotLoadedError;
import dao.DaoCountries;
import dao.retrofit.llamadas.CountryApi;
import dao.retrofit.modelo.ResponseCountryInfo;
import dao.retrofit.modelo.ResponseCountryItem;
import domain.modelo.Country;
import domain.modelo.CountryInfo;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class DaoCountriesImpl implements DaoCountries {

    private final CountryApi countryApi;

    @Inject
    public DaoCountriesImpl(CountryApi countryApi) {
        this.countryApi = countryApi;
    }

    public Either<ApiError, List<Country>> getCountriesRetrofitCall() {
        Either<ApiError, List<Country>> response = null;
        Response<List<ResponseCountryItem>> countriesResponseList;
        List<Country> countryList;
        try {
            countriesResponseList = countryApi.getAllCountries().execute();
            if (countriesResponseList.isSuccessful()) {
                assert countriesResponseList.body() != null;
                List<ResponseCountryItem> responseCountries = countriesResponseList.body();
                countryList = responseCountries.stream()
                        .map(responseCountryItem -> new Country(responseCountryItem.getId(), responseCountryItem.getName(), responseCountryItem.getIso2())).toList();
                response = Either.right(countryList);
            } else {
                assert countriesResponseList.errorBody() != null;
                countriesResponseList.errorBody().string();
                response = Either.left(new NotLoadedError(countriesResponseList.message(), LocalDateTime.now()));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }

    public Either<ApiError, CountryInfo> getCountryInfoRetrofitCall(Country country) {
        Either<ApiError, CountryInfo> response = null;
        Response<ResponseCountryInfo> countryInfoResponse;
        CountryInfo countryInfo;
        try {
            String ciso = country.getIso2();
            countryInfoResponse = countryApi.getCountryInfo(ciso).execute();
            if (countryInfoResponse.isSuccessful()) {
                assert countryInfoResponse.body() != null;
                ResponseCountryInfo responseCountryInfo = countryInfoResponse.body();
                countryInfo = new CountryInfo(responseCountryInfo.getName(), responseCountryInfo.getPhoneCode(), responseCountryInfo.getCapital(), responseCountryInfo.getCurrencyName(), responseCountryInfo.getTopLevelDomain(), responseCountryInfo.getNativeName(), responseCountryInfo.getSubregion());
                response = Either.right(countryInfo);
            } else {
                assert countryInfoResponse.errorBody() != null;
                countryInfoResponse.errorBody().string();
                response = Either.left(new NotLoadedError(countryInfoResponse.message(), LocalDateTime.now()));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
}