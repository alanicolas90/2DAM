package ui.screens.tableberrie;


import dao.DaoBerry;
import domain.modelo.berry.*;
import domain.service.BerriesService;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.screens.common.BaseScreenController;

import java.util.List;

public class TableBerriesController extends BaseScreenController {


    private final DaoBerry daoBerry;
    private final BerriesService berriesService;

    @Inject
    public TableBerriesController(DaoBerry daoBerry, BerriesService berriesService) {
        this.daoBerry = daoBerry;
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
        List<ResultsItem> resultsItems = daoBerry.getAllBerriesWithIdMod().get();
        tablePokemons.getItems().addAll(resultsItems);
    }

    public void showPokemonMarked() {
        ResultsItem resultsItem = tablePokemons.getSelectionModel().getSelectedItem();
        BerryResponse berryResponse = berriesService.getSpecificBerry(Integer.parseInt(resultsItem.getUrl())).get();
        txtId.setText("id: " + berryResponse.getId());
        txtName.setText("name: " + berryResponse.getName());
        txtSmoothness.setText("smoothness: " + berryResponse.getSmoothness());
        txtSize.setText("size: " + berryResponse.getSize());
        txtGrowthTIme.setText("growth time: " + berryResponse.getGrowth_time());
        txtMaxHarvest.setText("max harvest: " + berryResponse.getMax_harvest());
        txtNaturalGrowthPower.setText("natural gift power: " + berryResponse.getNatural_gift_power());


        txtFlavourSpicy.setText("spicy: " + berryResponse.getFlavors().get(0).getPotency());
        txtflavourDry.setText("dry: " + berryResponse.getFlavors().get(1).getPotency());
        txtFalvourSweet.setText("sweet: " + berryResponse.getFlavors().get(2).getPotency());
        txtFlavourBitter.setText("bitter: " + berryResponse.getFlavors().get(3).getPotency());
        txtFlavourSour.setText("sour: " + berryResponse.getFlavors().get(4).getPotency());


    }

    public void resetTableSearch() {
        tablePokemons.getItems().clear();
        tablePokemons.getItems().addAll(daoBerry.getAllBerriesWithIdMod().get());
    }

    public void searchBerryByName() {
        String nombreABuscar = txtPokemonSearch.getText();
        if (nombreABuscar.isEmpty() || nombreABuscar.isBlank()) {
            tablePokemons.getItems();
            tablePokemons.getItems().addAll(daoBerry.getAllBerriesWithIdMod().get());
        } else if (berriesService.filteresBerriesByName(nombreABuscar).isRight()) {
            tablePokemons.getItems().clear();
            tablePokemons.getItems().addAll(berriesService.filteresBerriesByName(nombreABuscar).get());

        }
    }
}
