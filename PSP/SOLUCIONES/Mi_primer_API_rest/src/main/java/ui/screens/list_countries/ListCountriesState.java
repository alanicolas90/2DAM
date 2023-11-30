package ui.screens.list_countries;

import common.ApiError;
import domain.modelo.Country;
import domain.modelo.CountryInfo;
import lombok.Data;

import java.util.List;

@Data
public class ListCountriesState {

    private final List<Country> countries;
    private final CountryInfo countryInfo;
    private final ApiError error;
    private final boolean loading;
    private final Country selectedCountry;
}