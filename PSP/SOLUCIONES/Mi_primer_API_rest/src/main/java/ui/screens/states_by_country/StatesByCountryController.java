package ui.screens.states_by_country;

import domain.modelo.Country;
import domain.modelo.State;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import ui.screens.common.BaseScreenController;


public class StatesByCountryController extends BaseScreenController {
    private final StatesByCountryViewModel statesByCountryViewModel;
    @FXML
    public AnchorPane screenStates;
    @FXML
    public MFXTableView<Country> countriesTable;
    @FXML
    public MFXTableColumn<Country> colCountryID;
    @FXML
    public MFXTableColumn<Country> colCountryName;
    @FXML
    public MFXTableView<State> statesTable;
    @FXML
    public MFXTableColumn<State> colStateID;
    @FXML
    public MFXTableColumn<State> colStateName;

    @Inject
    public StatesByCountryController(StatesByCountryViewModel statesByCountryViewModel) {
        this.statesByCountryViewModel = statesByCountryViewModel;
    }

    public void initialize() {
        colStateID.setRowCellFactory(state -> new MFXTableRowCell<>(State::getId));
        colStateName.setRowCellFactory(state -> new MFXTableRowCell<>(State::getName));
        colCountryID.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getId));
        colCountryName.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getName));
        countriesTable.getSelectionModel().selectionProperty().addListener((observableValue, oldItem, itemSelected) -> {
            if (itemSelected != null && !itemSelected.values().isEmpty()) {
                statesByCountryViewModel.selectCountry(itemSelected.values().stream().findFirst().orElse(null));
            }
        });
        stateChanges();
        statesByCountryViewModel.loadCountries();
    }

    @Override
    public void principalLoaded() {
        initialize();
    }


    private void stateChanges() {
        statesByCountryViewModel.getState().addListener((observableValue, oldStatesByCountryState, newStatesByCountryState) -> {
            if (newStatesByCountryState.getError() != null) {
                getPrincipalController().showErrorAlert(newStatesByCountryState.getError().getMessage());
            }
            if (newStatesByCountryState.getCountries() != null) {
                countriesTable.getItems().clear();
                countriesTable.getItems().addAll(newStatesByCountryState.getCountries());
            }
            if (newStatesByCountryState.getCountrySelected() != null) {
                statesTable.getItems().clear();
                statesTable.getItems().addAll(newStatesByCountryState.getStatesByCountry());
            }
            if (newStatesByCountryState.getStatesByCountry() != null) {
                statesTable.getItems().clear();
                statesTable.getItems().addAll(newStatesByCountryState.getStatesByCountry());
            } else {
                statesTable.getItems().clear();
            }
        });
    }
}