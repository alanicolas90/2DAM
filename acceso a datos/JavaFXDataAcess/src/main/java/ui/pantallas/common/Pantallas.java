package ui.pantallas.common;

import lombok.Getter;

@Getter
public enum Pantallas {

    LOGIN(ConstantesPantallas.FXML_LOGIN),
    ADD_CUSTOMER(ConstantesPantallas.FXML_ADD_CUSTOMER),
    REMOVE_CUSTOMER(ConstantesPantallas.FXML_REMOVE_CUSTOMER),
    UPDATE_CUSTOMER(ConstantesPantallas.FXML_UPDATE_CUSTOMER),
    LIST_CUSTOMER(ConstantesPantallas.FXML_LIST_CUSTOMER),
    BIENVENIDA(ConstantesPantallas.FXML_BIENVENIDA),
    LIST_ORDER(ConstantesPantallas.FXML_LIST_ORDERS),
    ADD_ORDER(ConstantesPantallas.FXML_ADD_ORDER),
    REMOVE_ORDER(ConstantesPantallas.FXML_REMOVE_ORDER),
    UPDATE_ORDER(ConstantesPantallas.FXML_UPDATE_ORDER);

    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }


}
