package ui.pantallas.pantalla1;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

public class Pantalla1Controller extends BasePantallaController {


    @FXML
    private MFXButton btChorra;
    @FXML
    private CheckBox checkState;
    @FXML
    private Label lbBienvenido;

    public void initialize(){

    }

    @Override
    public void principalCargado() {
        lbBienvenido.setText(getPrincipalController().getActualUser().nombre());
    }

    @FXML
    private void click(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacion");
        alert.setHeaderText("Informacion");
        alert.setContentText("Hola mundo");
        alert.showAndWait();
    }
}
