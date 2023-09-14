package ui.pantallas.common;

public enum Pantallas {

    LOGIN ("/fxml/login.fxml"),
    BIENVENIDA("/fxml/bienvenida.fxml");

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
