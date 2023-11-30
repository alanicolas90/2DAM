package domain.usecases;

import common.ApiError;
import dao.DaoCountries;
import domain.modelo.Country;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class LoadCountriesUseCase {

    private final DaoCountries daoCountries;

    @Inject
    public LoadCountriesUseCase(DaoCountries daoCountries) {
        this.daoCountries = daoCountries;
    }

    public Either<ApiError, List<Country>> getCountriesRetrofitCall() {
        return daoCountries.getCountriesRetrofitCall();
    }
}