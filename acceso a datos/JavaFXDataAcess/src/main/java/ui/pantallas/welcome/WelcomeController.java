package ui.pantallas.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BaseScreenController;

public class WelcomeController extends BaseScreenController {

    @FXML
    private Label txtWelcome;

    @Override
    public void principalCargado() {
        this.txtWelcome.setText("Welcome " + getPrincipalController().getUser());
    }
}
