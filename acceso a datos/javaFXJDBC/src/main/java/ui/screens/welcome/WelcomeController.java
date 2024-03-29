package ui.screens.welcome;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;

public class WelcomeController extends BaseScreenController {

    @FXML
    private Label txtWelcome;

    @Override
    public void principalLoaded() {
        this.txtWelcome.setText(ConstantsController.WELCOME + getPrincipalController().getUser());
    }
}
