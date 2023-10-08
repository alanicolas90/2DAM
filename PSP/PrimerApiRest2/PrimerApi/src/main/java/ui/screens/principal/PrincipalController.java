package ui.screens.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import common.constantes.Constantes;
import ui.screens.common.BaseScreenController;
import ui.screens.common.Screens;

import java.io.IOException;

@Log4j2
public class PrincipalController {

    // objet especial para DI
    Instance<Object> instance;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private Stage primaryStage;

    @FXML
    public BorderPane root;

    private final Alert alert;


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
        loadScreen(Screens.TABLE_POKEMONS);
    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuExit" -> exit(actionEvent);
            case "tablePokemons" -> loadScreen(Screens.TABLE_POKEMONS);
            case "tableBerries" -> loadScreen(Screens.TABLE_BERRIES);
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
        alert.setTitle(Constantes.EXIT);
        alert.setHeaderText(Constantes.EXIT);
        alert.setContentText(Constantes.ARE_YOU_SURE_YOU_WANT_TO_EXIT);
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }

    public void showInformation(String messageForUser, String titleInformation) {
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle(titleInformation);
        alert.setHeaderText(titleInformation);
        alert.setContentText(messageForUser);
        alert.showAndWait();
    }

}
