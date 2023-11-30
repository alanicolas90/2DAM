package domain.usecases;

import common.ApiError;
import dao.DaoCities;
import domain.modelo.City;
import domain.modelo.Country;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class LoadCitiesUseCase {
    private final DaoCities daoCities;

    @Inject
    public LoadCitiesUseCase(DaoCities daoCities) {
        this.daoCities = daoCities;
    }

    public Either<ApiError, List<City>> getCitiesRetrofitCall(Country country) {
        return daoCities.getCitiesRetrofitCall(country);
    }
}