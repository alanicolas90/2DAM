package ui.screens.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;

public class WelcomeController extends BaseScreenController {

    @FXML
    private Label txtWelcome;

    @Override
    public void principalCargado() {
        this.txtWelcome.setText(ConstantNormal.WELCOME + getPrincipalController().getUser());
    }
}
