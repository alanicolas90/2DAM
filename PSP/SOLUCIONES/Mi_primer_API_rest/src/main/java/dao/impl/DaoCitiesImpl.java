package dao.impl;

import common.ApiError;
import common.NotLoadedError;
import dao.DaoCities;
import dao.retrofit.llamadas.CityApi;
import dao.retrofit.modelo.ResponseCityItem;
import domain.modelo.City;
import domain.modelo.Country;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class DaoCitiesImpl implements DaoCities {

    private final CityApi cityApi;

    @Inject
    public DaoCitiesImpl(CityApi cityApi) {
        this.cityApi = cityApi;
    }

    public Either<ApiError, List<City>> getCitiesRetrofitCall(Country country) {
        Either<ApiError, List<City>> response = null;
        Response<List<ResponseCityItem>> citiesResponseList;
        List<City> cityList;
        try {
            String ciso = country.getIso2();
            citiesResponseList = cityApi.getCitiesByCountry(ciso).execute();
            if (citiesResponseList.isSuccessful()) {
                assert citiesResponseList.body() != null;
                List<ResponseCityItem> responseCities = citiesResponseList.body();
                cityList = responseCities.stream()
                        .map(responseCityItem -> new City(responseCityItem.getId(), responseCityItem.getName())).toList();
                response = Either.right(cityList);
            } else {
                assert citiesResponseList.errorBody() != null;
                citiesResponseList.errorBody().string();
                response = Either.left(new NotLoadedError(citiesResponseList.message(), LocalDateTime.now()));
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }
}