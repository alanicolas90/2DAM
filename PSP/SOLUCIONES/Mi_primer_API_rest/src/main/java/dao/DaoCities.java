package dao;

import common.ApiError;
import domain.modelo.City;
import domain.modelo.Country;
import io.vavr.control.Either;

import java.util.List;

public interface DaoCities {
    Either<ApiError, List<City>> getCitiesRetrofitCall(Country country);
}