package ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    LOGIN(ConstantScreen.FXML_LOGIN),
    BIENVENIDA(ConstantScreen.FXML_BIENVENIDA),
    TODOS_POKEMON(ConstantScreen.FXML_TODOS_POKEMON),
    TABLE_POKEMONS(ConstantScreen.FXML_TABLA_POKEMON);

    private final String route;

    Screens(String route) {
        this.route = route;
    }


}
