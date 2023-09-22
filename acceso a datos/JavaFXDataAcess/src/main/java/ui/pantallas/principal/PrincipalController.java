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
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.common.Screens;

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
    private String user;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void loadScreen(Screens pantalla) {
        changeScreen(loadScreen(pantalla.getRoute()));
    }


    private void changeScreen(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    private Pane loadScreen(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        loadScreen(Screens.LOGIN);
    }

    public void onLogin(String usuario) {
        this.user = usuario;
        loadScreen(Screens.BIENVENIDA);
        menuPrincipal.setVisible(true);
    }

    public void logout() {
        this.user = null;
        menuPrincipal.setVisible(false);
        loadScreen(Screens.LOGIN);
    }

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuExit" -> exit(actionEvent);
            case "menuLogout" -> logout();
            case "addCustomer" -> loadScreen(Screens.ADD_CUSTOMER);
            case "removeCustomer" -> loadScreen(Screens.REMOVE_CUSTOMER);
            case "updateCustomer" -> loadScreen(Screens.UPDATE_CUSTOMER);
            case "listCustomer" -> loadScreen(Screens.LIST_CUSTOMER);
            case "listOrder" -> loadScreen(Screens.LIST_ORDER);
            case "addOrder" -> loadScreen(Screens.ADD_ORDER);
            case "removeOrder" -> loadScreen(Screens.REMOVE_ORDER);
            case "updateOrder" -> loadScreen(Screens.UPDATE_ORDER);
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
        alert.setTitle("Exit");
        alert.setHeaderText("Exit");
        alert.setContentText("Are you sure you want to exit?");
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
