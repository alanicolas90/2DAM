package ui.screens.states_by_country;

import common.ApiError;
import domain.modelo.Country;
import domain.modelo.State;
import lombok.Data;

import java.util.List;

@Data
public class StatesByCountryState {
    private final List<State> statesByCountry;
    private final List<Country> countries;
    private final ApiError error;
    private final boolean loading;
    private final Country countrySelected;
}