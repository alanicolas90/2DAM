package ui.pantallas.common;

import lombok.Getter;

@Getter
public enum Pantallas {

    LOGIN (ConstantesPantallas.FXML_LOGIN);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }


}
