package ui.screens.cities_by_country;

import domain.modelo.City;
import domain.modelo.Country;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ui.screens.common.BaseScreenController;

public class CitiesByCountryController extends BaseScreenController {
    private final CitiesByCountryViewModel citiesByCountryViewModel;
    @FXML
    public AnchorPane screenCities;
    @FXML
    public MFXTableView<Country> countriesTable;
    @FXML
    public MFXTableColumn<Country> colCountryID;
    @FXML
    public MFXTableColumn<Country> colCountryName;
    @FXML
    public MFXTableView<City> citiesTable;
    @FXML
    public MFXTableColumn<City> colCityID;
    @FXML
    public MFXTableColumn<City> colCityName;

    @Inject
    public CitiesByCountryController(CitiesByCountryViewModel citiesByCountryViewModel) {
        this.citiesByCountryViewModel = citiesByCountryViewModel;
    }

    public void initialize() {
        colCityID.setRowCellFactory(city -> new MFXTableRowCell<>(City::getId));
        colCityName.setRowCellFactory(city -> new MFXTableRowCell<>(City::getName));
        colCountryID.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getId));
        colCountryName.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getName));
        countriesTable.getSelectionModel().selectionProperty().addListener((observableValue, oldItem, itemSelected) -> {
            if (itemSelected != null && !itemSelected.values().isEmpty()) {
                citiesByCountryViewModel.selectCountry(itemSelected.values().stream().findFirst().orElse(null));
            }
        });
        stateChanges();
        citiesByCountryViewModel.loadCountries();
    }

    @Override
    public void principalLoaded() {
        initialize();
    }


    private void stateChanges() {
        citiesByCountryViewModel.getState().addListener((observableValue, oldCitiesByCountryState, newCitiesByCountryState) -> {
            if (newCitiesByCountryState.getError() != null) {
                getPrincipalController().showErrorAlert(newCitiesByCountryState.getError().getMessage());
            }
            if (newCitiesByCountryState.getCountries() != null) {
                countriesTable.getItems().clear();
                countriesTable.getItems().addAll(newCitiesByCountryState.getCountries());
            }
            if (newCitiesByCountryState.getCountrySelected() != null) {
                citiesTable.getItems().clear();
                citiesTable.getItems().addAll(newCitiesByCountryState.getCitiesByCountry());
            }
            if (newCitiesByCountryState.getCitiesByCountry() != null) {
                citiesTable.getItems().clear();
                citiesTable.getItems().addAll(newCitiesByCountryState.getCitiesByCountry());
            } else {
                citiesTable.getItems().clear();
            }
        });
    }
}