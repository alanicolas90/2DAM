package ui.screens.welcome;

import dao.DaoPokemon;
import domain.modelo.pokemon.PokemonResponse;
import domain.modelo.pokemon.Sprites;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ui.screens.common.BaseScreenController;


public class WelcomeController extends BaseScreenController {

    private final DaoPokemon daoPokemon;
    @Inject
    public WelcomeController(DaoPokemon daoPokemon) {
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




    @Override
    public void principalCargado() {
        PokemonResponse pokemons = daoPokemon.getPokemonsById(1).get();
        Sprites sprites = pokemons.getSprites();
        Image imgPokemonNormal = new Image(sprites.getFront_default());
        Image imagePokemonShiny = new Image(sprites.getFront_shiny());

        imgViewPokemon.setImage(imgPokemonNormal);
        imgViewPokemonShiny.setImage(imagePokemonShiny);
        String idPokemon = String.valueOf(pokemons.getId());
        txtIdPokemon.setText(idPokemon);
        txtNamePokemon.setText(pokemons.getName());
    }
}
