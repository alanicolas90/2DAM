package ui.pantallas.bienvenida;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

public class BienvenidaController extends BasePantallaController {

    @FXML
    private Label bienvenida;

    @Override
    public void principalCargado(){
        bienvenida.setText("Bienvenido a " + getPrincipalController().getUsuario());
    }

}
