package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL ("/fxml/principal.fxml");


    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
