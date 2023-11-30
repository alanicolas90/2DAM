package ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    COUNTRIES(common.Constants.FXML_LIST_COUNTRIES_FXML),
    STATES(common.Constants.FXML_STATES_BY_COUNTRY_FXML),
    CITIES(common.Constants.FXML_CITIES_BY_COUNTRY_FXML);

    private final String path;

    Screens(String path) {
        this.path = path;
    }
}