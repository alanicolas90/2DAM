package ui.pantallas.common;

import lombok.Getter;

@Getter
public enum Pantallas {

    LOGIN(ConstantesPantallas.FXML_LOGIN),
    ADD_CUSTOMER(ConstantesPantallas.FXML_ADD_CUSTOMER),
    REMOVE_CUSTOMER(ConstantesPantallas.FXML_REMOVE_CUSTOMER),
    UPDATE_CUSTOMER(ConstantesPantallas.FXML_UPDATE_CUSTOMER),
    LIST_CUSTOMER(ConstantesPantallas.FXML_LISTA_CUSTOMER);

    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }


}
