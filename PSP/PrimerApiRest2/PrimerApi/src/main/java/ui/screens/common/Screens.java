package ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    TABLE_POKEMONS(ConstantScreen.FXML_TABLA_POKEMON),
    TABLE_BERRIES(ConstantScreen.FXML_TABLA_BERRIES);

    private final String route;

    Screens(String route) {
        this.route = route;
    }


}
