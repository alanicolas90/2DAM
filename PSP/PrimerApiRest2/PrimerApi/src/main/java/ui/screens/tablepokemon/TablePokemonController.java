package ui.screens.tablepokemon;

import dao.DaoPokemon;
import domain.modelo.pokemon.Result;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.screens.common.BaseScreenController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TablePokemonController extends BaseScreenController {

    @Inject
    private DaoPokemon daoPokemon;
    @FXML
    private TableView<Result> tablePokemons;
    @FXML
    private TableColumn<Result, String> columnId;
    @FXML
    private TableColumn<Result, String> columnName;

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("url"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @Override
    public void principalCargado() {
        List<Result> listaResult = daoPokemon.getAllPokemonsIds().get();

        List<Result> listaModificada = listaResult.stream().map(result ->{
                    String modifiedUrl = Arrays.stream(result.getUrl().split("/")).reduce((s, s2) -> s2).get();// Modify the URL
                    return new Result(result.getName(), modifiedUrl);
                }
               ).collect(Collectors.toList());

        tablePokemons.getItems().addAll(listaModificada);
    }
}
