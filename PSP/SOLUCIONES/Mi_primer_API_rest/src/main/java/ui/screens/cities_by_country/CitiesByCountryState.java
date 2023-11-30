package ui.screens.cities_by_country;

import common.ApiError;
import domain.modelo.City;
import domain.modelo.Country;
import lombok.Data;

import java.util.List;

@Data
public class CitiesByCountryState {
    private final List<City> citiesByCountry;
    private final List<Country> countries;
    private final ApiError error;
    private final boolean loading;
    private final Country countrySelected;
}