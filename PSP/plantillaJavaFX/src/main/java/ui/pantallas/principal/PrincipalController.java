package ui.pantallas.principal;


import domain.modelo.Cromo;
import domain.modelo.Usuario;
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
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;
import java.util.Optional;


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

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
       this.instance = instance;
       alert= new Alert(Alert.AlertType.NONE);
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
//
//                cambioPantalla(pantallaBienvenida);
//                break;
            default:
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
                break;
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

        }
        return panePantalla;
    }







    private void cambioPantalla(Pane pantallaNueva) {

        //            FadeTransition fade = new FadeTransition();
//            fade.setNode(root.getCenter());
//            fade.setDuration(Duration.millis(2000));
//            fade.setCycleCount(1);
//            fade.setInterpolator(Interpolator.LINEAR);
//            fade.setFromValue(1);
//            fade.setToValue(0);
//            fade.play();
//            fade.setOnFinished(event -> {
//                root.setCenter(panePantalla);
//            });
        //            TranslateTransition translate = new TranslateTransition();
//            translate.setNode(stackPane.getChildren().get(1));
//            translate.setDuration(Duration.millis(1000));
//            translate.setCycleCount(1);
//            translate.setInterpolator(Interpolator.LINEAR);
//            translate.setFromX(0);
//            translate.setToX(root.getWidth());
//            translate.play();
//            translate.setOnFinished(event -> {
//                stackPane.getChildren().remove(1);
//            });

//        StackPane stackPane = (StackPane) root.getCenter();
//
//        if (stackPane.getChildren().get(0) != pantallaNueva) {
//
//            stackPane.getChildren().add(0, pantallaNueva);
//
//            ScaleTransition scaleTransition = new ScaleTransition();
//            scaleTransition.setNode(stackPane.getChildren().get(1));
//            scaleTransition.setDuration(Duration.millis(500));
//            scaleTransition.setFromX(stackPane.getChildren().get(1).getScaleX());
//            scaleTransition.setFromY(stackPane.getChildren().get(1).getScaleY());
//            scaleTransition.setToX(0);
//            scaleTransition.setToY(0);
//            scaleTransition.setInterpolator(Interpolator.EASE_OUT);
//            scaleTransition.play();
//            scaleTransition.setOnFinished(event -> {
//                Node node = stackPane.getChildren().remove(1);
//                node.setScaleX(1);
//                node.setScaleY(1);
//            });
//        }

        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.PRINCIPAL);

    }




//    @FXML
//    private void menuClick(ActionEvent actionEvent) {
//        switch (((MenuItem) actionEvent.getSource()).getId()) {
//            case "menuItemPantalla1" -> cargarPantalla(Pantallas.PANTALLA1);
//            case "menuItemListado" -> cargarPantalla(Pantallas.LISTADO);
//            case "menuItemPantallaNueva" -> cargarPantalla(Pantallas.PANTALLANUEVA);
//        }
//    }

    //evento de otra pantalla
}
