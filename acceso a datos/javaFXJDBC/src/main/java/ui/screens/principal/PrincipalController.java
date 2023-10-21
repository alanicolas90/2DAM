package ui.screens.principal;


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
import model.Credential;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.common.Screens;

import java.io.IOException;

@Log4j2
public class PrincipalController {

    // objet especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @FXML
    public BorderPane root;

    private final Alert alert;

    @Getter
    private String user;

    @Getter
    private int idUserLogged;
    @FXML
    private Menu menuCustomer;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void loadScreen(Screens screen) {
        changeScreen(loadScreen(screen.getRoute()));
    }


    private void changeScreen(Pane newScreen) {
        root.setCenter(newScreen);
    }

    private Pane loadScreen(String ruta) {
        Pane paneScreen = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            paneScreen = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController screenController = fxmlLoader.getController();
            screenController.setPrincipalController(this);
            screenController.principalCargado();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return paneScreen;
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        loadScreen(Screens.LOGIN);
    }

    public void onLogin(Credential credential) {
        this.idUserLogged = credential.getIdCustomer();
        this.user = credential.getUsername();
        menuPrincipal.setVisible(true);
        if(!credential.isPrivilege()){
            menuCustomer.setVisible(false);
        }
        loadScreen(Screens.BIENVENIDA);
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
        alert.setTitle(ConstantNormal.EXIT);
        alert.setHeaderText(ConstantNormal.EXIT);
        alert.setContentText(ConstantNormal.ARE_YOU_SURE_YOU_WANT_TO_EXIT);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    public void alertWarning(String messageForUser, String titleWarning) {
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle(titleWarning);
        alert.setHeaderText(titleWarning);
        alert.setContentText(messageForUser);

        alert.showAndWait();
    }

    public boolean alertDeleteConfirmation(String messageForUser, String titleWarning) {
        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType acceptButton = new ButtonType("Accept");
        ButtonType denyButton = new ButtonType("Deny");

        alertDelete.setAlertType(Alert.AlertType.CONFIRMATION);
        alertDelete.setTitle(titleWarning);
        alertDelete.setHeaderText(titleWarning);
        alertDelete.setContentText(messageForUser);
        alertDelete.getButtonTypes().setAll(acceptButton, denyButton);
        alertDelete.showAndWait();
        return alertDelete.getResult() == acceptButton;
    }

    public void showInformation(String messageForUser, String titleInformation) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle(titleInformation);
        alert.setHeaderText(titleInformation);
        alert.setContentText(messageForUser);
        alert.showAndWait();
    }
}
