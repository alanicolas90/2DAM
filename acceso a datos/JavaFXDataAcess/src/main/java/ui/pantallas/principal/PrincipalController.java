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

@Log4j2
public class PrincipalController {

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

    private void cargarPantalla(Pantallas pantalla) {
        cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }


    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
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


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void onLogin(String usuario) {
        this.usuario = usuario;
        cargarPantalla(Pantallas.BIENVENIDA);
        menuPrincipal.setVisible(true);
    }

    public void logout() {
        this.usuario = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuExit" -> exit(actionEvent);
            case "menuLogout" -> logout();
            case "addCustomer" -> cargarPantalla(Pantallas.ADD_CUSTOMER);
            case "removeCustomer" -> cargarPantalla(Pantallas.REMOVE_CUSTOMER);
            case "updateCustomer" -> cargarPantalla(Pantallas.UPDATE_CUSTOMER);
            case "listCustomer" -> cargarPantalla(Pantallas.LIST_CUSTOMER);
            case "listOrder" -> cargarPantalla(Pantallas.LIST_ORDER);
            case "addOrder" -> cargarPantalla(Pantallas.ADD_ORDER);
            case "removeOrder" -> cargarPantalla(Pantallas.REMOVE_ORDER);
            case "updateOrder" -> cargarPantalla(Pantallas.UPDATE_ORDER);
            default -> alertExit();
        }
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    public void exit(ActionEvent actionEvent) {
        if (alertExit()) {
            primaryStage.close();
        } else {
            actionEvent.consume();
        }
    }

    private boolean alertExit() {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText("Salir");
        alert.setContentText("¿Está seguro que desea salir?");
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    public void alertWarning(String s, String titleWarning) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle(titleWarning);
        alert.setHeaderText(titleWarning);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void showInformation(String s, String titleInformation){
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle(titleInformation);
        alert.setHeaderText(titleInformation);
        alert.setContentText(s);
        alert.showAndWait();
    }
}
