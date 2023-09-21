package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.LoginService;
import ui.pantallas.common.BasePantallaController;

public class LoginController extends BasePantallaController{

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    @Inject
    private LoginService loginService;

    @FXML
    private void login() {
        if(loginService.login(username.getText(), password.getText())){
            getPrincipalController().onLogin(username.getText());
        }else{
            getPrincipalController().alertWarning("Usuario o contraseña incorrectos","Error");
        }
    }
}
