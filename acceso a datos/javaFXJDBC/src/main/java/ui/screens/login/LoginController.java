package ui.screens.login;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Credential;
import model.ErrorC;
import service.LoginService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;

public class LoginController extends BaseScreenController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @Inject
    private LoginService loginService;

    @FXML
    private void login() {
        Credential credential = new Credential(username.getText(), password.getText());
        Either<ErrorC, Credential> eitherCredential= loginService.get(credential);
        if (eitherCredential.isRight()) {
            getPrincipalController().onLogin(eitherCredential.get());
        } else {
            getPrincipalController().alertWarning(ConstantsController.USERNAME_OR_PASSWORD_INCORRECT, ConstantsController.ERROR);
        }
    }
}
