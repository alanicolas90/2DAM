package ui.screens.login;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Credential;
import service.LoginService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @Inject
    private LoginService loginService;

    @FXML
    private void login() {
        Credential credential = loginService.login(username.getText(), password.getText());
        if (credential != null) {
            getPrincipalController().onLogin(credential);
        } else {
            getPrincipalController().alertWarning(ConstantNormal.USERNAME_OR_PASSWORD_INCORRECT, ConstantNormal.ERROR);
        }
    }
}
