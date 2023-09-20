package ui.pantallas.common;

import lombok.Getter;

@Getter
public enum Pantallas {

    LOGIN (ConstantesPantallas.FXML_LOGIN),
    ADD_CUSTOMER(ConstantesPantallas.FXML_ADD_CUSTOMER);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }


}
