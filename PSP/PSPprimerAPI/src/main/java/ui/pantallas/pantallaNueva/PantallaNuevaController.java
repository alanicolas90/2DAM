package ui.pantallas.pantallaNueva;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.ResourceBundle;

public class PantallaNuevaController extends BasePantallaController  implements Initializable {


    @FXML
    private Label lbBienvenida;



    public PantallaNuevaController() {


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @Override
    public void principalCargado() {
        //el principal cargado
        String user = getPrincipalController().getActualUser().nombre();
        lbBienvenida.setText("Bienvenido a la pantalla de nuevo "+user+"!");
    }
}
