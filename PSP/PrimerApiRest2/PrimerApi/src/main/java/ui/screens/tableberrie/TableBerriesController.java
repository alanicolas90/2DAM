package ui.screens.tableberrie;


import dao.DaoBerry;
import domain.modelo.berry.*;
import domain.service.BerriesService;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import common.constantes.Constantes;
import ui.screens.common.BaseScreenController;

import java.util.List;

public class TableBerriesController extends BaseScreenController {


    private final BerriesService berriesService;

    @Inject
    public TableBerriesController(BerriesService berriesService) {
        this.berriesService = berriesService;
    }


    @FXML
    private Label txtFlavourSpicy;
    @FXML
    private Label txtflavourDry;
    @FXML
    private Label txtFalvourSweet;
    @FXML
    private Label txtFlavourBitter;
    @FXML
    private Label txtFlavourSour;
    @FXML
    private Label txtSmoothness;
    @FXML
    private Label txtSize;
    @FXML
    private Label txtGrowthTIme;
    @FXML
    private Label txtNaturalGrowthPower;
    @FXML
    private Label txtMaxHarvest;
    @FXML
    private Label txtName;
    @FXML
    private Label txtId;
    @FXML
    private TextField txtPokemonSearch;
    @FXML
    private TableColumn<ResultsItem, String> columnName;
    @FXML
    private TableColumn<ResultsItem, String> columnId;
    @FXML
    private TableView<ResultsItem> tablePokemons;

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("url"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    public void principalCargado() {
        List<ResultsItem> resultsItems = berriesService.getAllBerriesResult().get();
        tablePokemons.getItems().addAll(resultsItems);
    }

    public void showPokemonMarked() {
        ResultsItem resultsItem = tablePokemons.getSelectionModel().getSelectedItem();
        if (berriesService.getSpecificBerry(Integer.parseInt(resultsItem.getUrl())).isLeft()) {
            getPrincipalController().showInformation(Constantes.NO_HAY_BERRIES_CON_ESE_NOMBRE, Constantes.ERROR);
            setAllTextsToEmpty();


        } else {
            BerryResponse berryResponse = berriesService.getSpecificBerry(Integer.parseInt(resultsItem.getUrl())).get();
            txtId.setText(Constantes.ID + berryResponse.getId());
            txtName.setText(Constantes.NAME + berryResponse.getName());
            txtSmoothness.setText(Constantes.SMOOTHNESS + berryResponse.getSmoothness());
            txtSize.setText(Constantes.SIZE + berryResponse.getSize());
            txtGrowthTIme.setText(Constantes.GROWTH_TIME + berryResponse.getGrowth_time());
            txtMaxHarvest.setText(Constantes.MAX_HARVEST + berryResponse.getMax_harvest());
            txtNaturalGrowthPower.setText(Constantes.NATURAL_GIFT_POWER + berryResponse.getNatural_gift_power());


            txtFlavourSpicy.setText(Constantes.SPICY + berryResponse.getFlavors().get(0).getPotency());
            txtflavourDry.setText(Constantes.DRY + berryResponse.getFlavors().get(1).getPotency());
            txtFalvourSweet.setText(Constantes.SWEET + berryResponse.getFlavors().get(2).getPotency());
            txtFlavourBitter.setText(Constantes.BITTER + berryResponse.getFlavors().get(3).getPotency());
            txtFlavourSour.setText(Constantes.SOUR + berryResponse.getFlavors().get(4).getPotency());
        }
    }

    public void resetTableSearch() {
        tablePokemons.getItems().clear();
        tablePokemons.getItems().addAll(berriesService.getAllBerriesResult().get());
    }

    public void searchBerryByName() {
        String nombreABuscar = txtPokemonSearch.getText();
        if (nombreABuscar.isEmpty() || nombreABuscar.isBlank()) {
            tablePokemons.getItems();
            tablePokemons.getItems().addAll(berriesService.getAllBerriesResult().get());
        } else if (berriesService.filteresBerriesByName(nombreABuscar).isRight()) {
            tablePokemons.getItems().clear();
            tablePokemons.getItems().addAll(berriesService.filteresBerriesByName(nombreABuscar).get());

        }
    }


    private void setAllTextsToEmpty() {
        txtId.setText(Constantes.ID);
        txtName.setText(Constantes.NAME);
        txtSmoothness.setText(Constantes.SMOOTHNESS);
        txtSize.setText(Constantes.SIZE);
        txtGrowthTIme.setText(Constantes.GROWTH_TIME);
        txtMaxHarvest.setText(Constantes.MAX_HARVEST);
        txtNaturalGrowthPower.setText(Constantes.NATURAL_GIFT_POWER);
        txtFlavourSpicy.setText(Constantes.SPICY);
        txtflavourDry.setText(Constantes.DRY);
        txtFalvourSweet.setText(Constantes.SWEET);
        txtFlavourBitter.setText(Constantes.BITTER);
        txtFlavourSour.setText(Constantes.SOUR);
    }
}
