package ui.screens.common;

import lombok.Getter;

@Getter
public enum Screens {

    LOGIN(ConstantScreen.FXML_LOGIN),
    ADD_CUSTOMER(ConstantScreen.FXML_ADD_CUSTOMER),
    REMOVE_CUSTOMER(ConstantScreen.FXML_REMOVE_CUSTOMER),
    UPDATE_CUSTOMER(ConstantScreen.FXML_UPDATE_CUSTOMER),
    LIST_CUSTOMER(ConstantScreen.FXML_LIST_CUSTOMER),
    BIENVENIDA(ConstantScreen.FXML_BIENVENIDA),
    LIST_ORDER(ConstantScreen.FXML_LIST_ORDERS),
    ADD_ORDER(ConstantScreen.FXML_ADD_ORDER),
    REMOVE_ORDER(ConstantScreen.FXML_REMOVE_ORDER),
    UPDATE_ORDER(ConstantScreen.FXML_UPDATE_ORDER);

    private final String route;

    Screens(String route) {
        this.route = route;
    }


}
