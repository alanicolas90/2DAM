package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;
import java.util.Objects;

@Log4j2
public class PrincipalController {

    @FXML
    private Menu menuHelp;

    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @FXML
    public BorderPane root;

    private final Alert alert;

    @Getter
    private String usuario;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }
    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        //alert.getDialogPane().lookupButton(ButtonType.CANCEL).setId("btn-cancel");
        alert.showAndWait();
    }

    private void cargarPantalla(Pantallas pantalla) {
        switch (pantalla) {
//            case LISTADO:
//                cambioPantalla(cargarPantalla(pantalla.getRuta()));
//                break;
//            case PANTALLA1:
//                if (pantallaBienvenida == null){
//                    pantallaBienvenida = cargarPantalla(pantalla.getRuta());
//                }
//                cambioPantalla(pantallaBienvenida);
//                break;
            default -> cambioPantalla(cargarPantalla(pantalla.getRuta()));
        }
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Ayuda");
        alert.setContentText("Este es un mensaje de ayuda");
        alert.showAndWait();
    }

    @FXML
    private void cambiarcss(ActionEvent actionEvent) {
        primaryStage.getScene().getRoot().getStylesheets().clear();
        primaryStage.getScene().getRoot().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/darkmode.css")).toExternalForm());
    }

    public void onLogin(String usuario){
        this.usuario = usuario;
        cargarPantalla(Pantallas.BIENVENIDA);
        menuPrincipal.setVisible(true);
    }

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuItemPantalla1" -> {}
            case "menuItemListado" ->{}
            case "menuItemPantallaNueva" -> {}
            case "menuItemLogout" -> {}//logout();
        }
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        //primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }
}
