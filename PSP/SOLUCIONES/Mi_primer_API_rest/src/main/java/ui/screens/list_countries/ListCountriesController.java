package ui.screens.list_countries;

import common.Constants;
import domain.modelo.Country;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ui.screens.common.BaseScreenController;

public class ListCountriesController extends BaseScreenController {

    private final ListCountriesViewModel listCountriesViewModel;
    @FXML
    public AnchorPane screenCountries;
    @FXML
    private MFXTableColumn<Country> colID;
    @FXML
    private MFXTableColumn<Country> colName;
    @FXML
    private MFXTableColumn<Country> colISO;
    @FXML
    private MFXTableView<Country> countriesTable;
    @FXML
    private Label nameLabel;
    @FXML
    private Label phoneCodeLabel;
    @FXML
    private Label capitalLabel;
    @FXML
    private Label currencyNameLabel;
    @FXML
    private Label tldLabel;
    @FXML
    private Label nativeNameLabel;
    @FXML
    private Label subregionLabel;

    @Inject
    public ListCountriesController(ListCountriesViewModel listCountriesViewModel) {
        this.listCountriesViewModel = listCountriesViewModel;
    }

    private void initialize() {
        colID.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getId));
        colName.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getName));
        colISO.setRowCellFactory(country -> new MFXTableRowCell<>(Country::getIso2));
        countriesTable.getSelectionModel().selectionProperty().addListener((observableValue, oldItem, itemSelected) -> {
            if (itemSelected != null && !itemSelected.values().isEmpty()) {
                listCountriesViewModel.selectCountry(itemSelected.values().stream().findFirst().orElse(null));
            }
        });
        stateChanges();
        listCountriesViewModel.loadCountries();
    }

    @Override
    public void principalLoaded() {
        initialize();
    }

    private void stateChanges() {
        listCountriesViewModel.getState().addListener((observableValue, oldListCountriesState, newListCountriesState) -> {
            if (newListCountriesState.getError() != null) {
                getPrincipalController().showErrorAlert(newListCountriesState.getError().getMessage());
            }
            if (newListCountriesState.getCountries() != null) {
                countriesTable.getItems().clear();
                countriesTable.getItems().addAll(newListCountriesState.getCountries());
            }
            if (newListCountriesState.getCountryInfo() != null) {
                nameLabel.setText(Constants.NAME + newListCountriesState.getCountryInfo().getName());
                phoneCodeLabel.setText(Constants.PHONE_CODE + newListCountriesState.getCountryInfo().getPhoneCode());
                capitalLabel.setText(Constants.CAPITAL + newListCountriesState.getCountryInfo().getCapital());
                currencyNameLabel.setText(Constants.CURRENCY_NAME + newListCountriesState.getCountryInfo().getCurrencyName());
                tldLabel.setText(Constants.TOP_LEVEL_DOMAIN + newListCountriesState.getCountryInfo().getTopLevelDomain());
                nativeNameLabel.setText(Constants.NATIVE_NAME + newListCountriesState.getCountryInfo().getNativeName());
                subregionLabel.setText(Constants.SUBREGION + newListCountriesState.getCountryInfo().getSubregion());
            } else {
                nameLabel.setText(Constants.NAME);
                phoneCodeLabel.setText(Constants.PHONE_CODE);
                capitalLabel.setText(Constants.CAPITAL);
                currencyNameLabel.setText(Constants.CURRENCY_NAME);
                tldLabel.setText(Constants.TOP_LEVEL_DOMAIN);
                nativeNameLabel.setText(Constants.NATIVE_NAME);
                subregionLabel.setText(Constants.SUBREGION);
            }
        });
    }
}