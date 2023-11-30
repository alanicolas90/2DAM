package ui.screens.principal;


import common.Constants;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.screens.common.BaseScreenController;
import ui.screens.common.Screens;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    @FXML
    public Menu menuView;
    @FXML
    public MenuItem menuItemCountries;
    @FXML
    public MenuItem menuItemStates;
    @FXML
    public MenuItem menuItemCities;
    @FXML
    public MenuItem menuItemExit;
    Instance<Object> instance; //Special object for DI

    @FXML
    private MenuBar principalMenu;
    private Stage primaryStage;

    @FXML
    public BorderPane root;

    private final Alert alert;

    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void loadScreen(Screens screen) {
        changeScreen(loadScreen(screen.getPath()));
    }

    public void showErrorAlert(String message) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.getDialogPane().setId(Constants.ALERT);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constants.BTN_OK);
        alert.showAndWait();
    }

    private Pane loadScreen(String ruta) {
        Pane paneScreen = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            paneScreen = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController screenController = fxmlLoader.getController();
            screenController.setPrincipalController(this);
            screenController.principalLoaded();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return paneScreen;
    }

    private void changeScreen(Pane newScreen) {
        root.setCenter(newScreen);
    }


    public void initialize() {
        principalMenu.setVisible(false);
        loadScreen(Screens.COUNTRIES);
    }

    private void closeWindowEvent(WindowEvent event) {
        Alert myAlert = new Alert(Alert.AlertType.INFORMATION);
        myAlert.getButtonTypes().remove(ButtonType.OK);
        myAlert.getButtonTypes().add(ButtonType.CANCEL);
        myAlert.getButtonTypes().add(ButtonType.YES);
        myAlert.setTitle(Constants.QUIT_APPLICATION);
        myAlert.setContentText(Constants.CLOSE_WITHOUT_SAVING);
        myAlert.initOwner(primaryStage.getOwner());
        Optional<ButtonType> res = myAlert.showAndWait();

        res.ifPresent(buttonType -> {
            if (buttonType == ButtonType.CANCEL) {
                event.consume();
            }
        });
    }

    public void exit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
        principalMenu.setVisible(true);
        primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
    }

    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case Constants.MENU_ITEM_COUNTRIES -> loadScreen(Screens.COUNTRIES);
            case Constants.MENU_ITEM_STATES -> loadScreen(Screens.STATES);
            case Constants.MENU_ITEM_CITIES -> loadScreen(Screens.CITIES);
            case Constants.MENU_ITEM_EXIT -> exit();
            default -> {
                //Empty
            }
        }
    }
}