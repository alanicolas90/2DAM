package client.ui.screens.login;

import domain.common.ApiError;
import lombok.Data;

@Data
public class LoginState {
    private final boolean isLoginOk;
    private final boolean loading;
    private final ApiError error;
    private final String messageRegisterSuccess;

    public LoginState(boolean isLoginOk, boolean loading, ApiError error, String messageRegisterSuccess) {
        this.isLoginOk = isLoginOk;
        this.loading = loading;
        this.error = error;
        this.messageRegisterSuccess = messageRegisterSuccess;
    }
}