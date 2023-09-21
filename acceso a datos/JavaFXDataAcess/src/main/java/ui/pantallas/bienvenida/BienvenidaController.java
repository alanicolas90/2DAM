package ui.pantallas.bienvenida;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

public class BienvenidaController extends BasePantallaController {

    @FXML
    private Label txtBienvenida;

    public void initialize(){
    }

    @Override
    public void principalCargado() {
        this.txtBienvenida.setText("Bienvenido " + getPrincipalController().getUsuario());
    }
}
