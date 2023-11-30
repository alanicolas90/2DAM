package client.ui.screens.login;

import client.ui.screens.common.BaseScreenController;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class LoginController extends BaseScreenController {
    private final LoginViewModel loginViewModel;
    @FXML
    private MFXPasswordField tvPassword;
    @FXML
    private MFXTextField tvUsername;

    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void principalLoaded() {
        stateChanges();
    }

    private void stateChanges() {
        loginViewModel.getState().addListener((observableValue, oldLoginState, newLoginState) -> Platform.runLater(() -> {
            getPrincipalController().isLoading(newLoginState.isLoading());
            if (newLoginState.isLoginOk()) {
                getPrincipalController().onLoginDone();
            }
            if (newLoginState.getError() != null) {
                getPrincipalController().showAlert(newLoginState.getError().getMessage(), Alert.AlertType.ERROR);
            }
            if (newLoginState.getMessageRegisterSuccess() != null) {
                getPrincipalController().showAlert(newLoginState.getMessageRegisterSuccess(), Alert.AlertType.INFORMATION);
            }
        }));
    }

    public void tryLogin() {
        loginViewModel.login(tvUsername.getText(), tvPassword.getText());
    }

    public void tryRegister() {
        loginViewModel.register(tvUsername.getText(), tvPassword.getText());
    }

}