package ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    LOGIN(ConstantScreen.FXML_LOGIN),
    BIENVENIDA(ConstantScreen.FXML_BIENVENIDA);

    private final String route;

    Screens(String route) {
        this.route = route;
    }


}
