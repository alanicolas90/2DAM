package ui.screens.tablepokemon;


import domain.modelo.pokemon.Pokemon;
import domain.modelo.pokemon.Result;
import domain.service.PokemonService;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.screens.common.BaseScreenController;


import java.util.List;


public class TablePokemonController extends BaseScreenController {

    private final PokemonService pokemonService;

    @Inject
    public TablePokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @FXML
    private ImageView frontViewShinyImg;
    @FXML
    private ImageView frontViewNormalImg;
    @FXML
    private Label labelHp;
    @FXML
    private TextField txtPokemonSearch;
    @FXML
    private TableView<Result> tablePokemons;
    @FXML
    private TableColumn<Result, String> columnId;
    @FXML
    private TableColumn<Result, String> columnName;


    @FXML
    private Label labelAttack;
    @FXML
    private Label labelDefense;
    @FXML
    private Label labelSPAttack;
    @FXML
    private Label labelSPDefense;
    @FXML
    private Label labelSpeed;


    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("url"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    public void principalCargado() {
        List<Result> listaResult = pokemonService.getAllPokemonsIds().get();
        tablePokemons.getItems().addAll(listaResult);
    }

    public void searchPokemonByName() {
        String nombrePokemon = txtPokemonSearch.getText();

        if (nombrePokemon.isEmpty() || nombrePokemon.isBlank()) {
            tablePokemons.getItems().clear();
            tablePokemons.getItems().addAll(pokemonService.getAllPokemonsIds().get());
        } else if (pokemonService.getAllPokemonsIdsFiltered(nombrePokemon).isRight()) {
            tablePokemons.getItems().clear();
            tablePokemons.getItems().addAll(pokemonService.getAllPokemonsIdsFiltered(nombrePokemon).get());

        } else {
            getPrincipalController().showInformation("No hay pokemons con ese nombre", "Null");
        }
    }

    public void showPokemonMarked() {
        Result pokemonSeleccionado = tablePokemons.getSelectionModel().getSelectedItem();

        int idPokemon = Integer.parseInt(pokemonSeleccionado.getUrl());
        Pokemon pokemon = pokemonService.getPokemonById(idPokemon).get();
        frontViewNormalImg.setImage(null);
        frontViewShinyImg.setImage(null);
        labelHp.setText("hp:");
        labelAttack.setText("attack:");
        labelDefense.setText("defense:");
        labelSPAttack.setText("special-attack:");
        labelSPDefense.setText("special-defense:");
        labelSpeed.setText("speed:");

        if (pokemon.getSprites().getFront_default() == null) {
            getPrincipalController().showInformation("Pokemon no disponible", "Error");
        } else if (pokemonService.getPokemonById(idPokemon).get().getSprites().getFront_default() != null) {

            labelHp.setText(pokemon.getStats().get(0).getStat().getName() + " : " + pokemon.getStats().get(0).getBase_stat());
            labelAttack.setText(pokemon.getStats().get(1).getStat().getName() + " : " + pokemon.getStats().get(1).getBase_stat());
            labelDefense.setText(pokemon.getStats().get(2).getStat().getName() + " : " + pokemon.getStats().get(2).getBase_stat());
            labelSPAttack.setText(pokemon.getStats().get(3).getStat().getName() + " : " + pokemon.getStats().get(3).getBase_stat());
            labelSPDefense.setText(pokemon.getStats().get(4).getStat().getName() + " : " + pokemon.getStats().get(4).getBase_stat());
            labelSpeed.setText(pokemon.getStats().get(5).getStat().getName() + " : " + pokemon.getStats().get(5).getBase_stat());


            Image imgPokemonNormal = new Image(pokemon.getSprites().getFront_default());
            frontViewNormalImg.setImage(imgPokemonNormal);

            if (pokemonService.getPokemonById(idPokemon).get().getSprites().getFront_shiny() != null) {
                Image imgPokemonShiny = new Image(pokemonService.getPokemonById(idPokemon).get().getSprites().getFront_shiny());
                frontViewShinyImg.setImage(imgPokemonShiny);
            }

        } else {
            getPrincipalController().showInformation("No pokemon selected", "Error");
        }

    }


    public void resetTableSearch() {
        tablePokemons.getItems().clear();
        tablePokemons.getItems().addAll(pokemonService.getAllPokemonsIds().get());
        txtPokemonSearch.setText("");
    }

}
