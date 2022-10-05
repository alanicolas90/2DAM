package ui.pantallas.common;

public enum Pantallas {

    PANTALLA1 (ConstantesPantallas.FXML_PANTALLA_1_FXML),
    LOGIN (ConstantesPantallas.FXML_LOGIN_FXML),
    SEARCH(ConstantesPantallas.FXML_SEARCH_FXML),
    ALCOHOLIC_DRINKS(ConstantesPantallas.FXML_ALCOHOLIC_DRINKS_FXML),
    NON_ALCOHOLIC_DRINKS(ConstantesPantallas.FXML_NON_ALCOHLIC_DRINKS_FXML),
    PANTALLANUEVA (ConstantesPantallas.FXML_RANDOM_DRINKS_FXML);

    private final String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
