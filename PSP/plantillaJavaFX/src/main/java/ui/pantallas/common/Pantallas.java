package ui.pantallas.common;

public enum Pantallas {

    LOGIN (ConstantesPantallas.FXML_LOGIN);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
