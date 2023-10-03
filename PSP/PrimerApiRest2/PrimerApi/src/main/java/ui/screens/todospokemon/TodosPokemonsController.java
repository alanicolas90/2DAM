package ui.screens.todospokemon;

import dao.DaoPokemon;
import domain.modelo.pokemon.PokemonResponse;
import domain.modelo.pokemon.Sprites;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.screens.common.BaseScreenController;


public class TodosPokemonsController extends BaseScreenController {

    private final DaoPokemon daoPokemon;
    @Inject
    public TodosPokemonsController(DaoPokemon daoPokemon) {
        this.daoPokemon = daoPokemon;
    }



    @FXML
    private Label txtNamePokemon;
    @FXML
    private Label txtIdPokemon;

    @FXML
    private ImageView imgViewPokemonShiny;
    @FXML
    private ImageView imgViewPokemon;

    @FXML
    private TextField pokedexId;
    @FXML
    private Button getPokemon;
    @FXML
    private Label stat;


    public void showPokemon() {
        String stringPokedexid = pokedexId.getText();
        int idPokedex = Integer.parseInt(stringPokedexid);

        PokemonResponse pokemons = daoPokemon.getPokemonsById(idPokedex).get();
        Sprites sprites = pokemons.getSprites();
        Image imgPokemonNormal = new Image(sprites.getFront_default());
        Image imagePokemonShiny = new Image(sprites.getFront_shiny());

        imgViewPokemon.setImage(imgPokemonNormal);
        imgViewPokemonShiny.setImage(imagePokemonShiny);
        String idPokemon = String.valueOf(pokemons.getId());
        txtIdPokemon.setText(idPokemon);
        txtNamePokemon.setText(pokemons.getName());
        stat.setText(String.valueOf(pokemons.getStats().get(0).getBase_stat()));
    }
}
