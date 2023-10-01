package ui.screens.login;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import domain.service.LoginService;
import ui.screens.common.BaseScreenController;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @Inject
    private LoginService loginService;

    @FXML
    private void login() {
        if (loginService.login(username.getText(), password.getText())) {
            getPrincipalController().onLogin(username.getText());
        } else {
            getPrincipalController().alertWarning("Username or password incorrect", "Error");
        }
    }
}
