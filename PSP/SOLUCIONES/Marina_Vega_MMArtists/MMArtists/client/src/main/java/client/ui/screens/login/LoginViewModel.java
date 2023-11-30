package client.ui.screens.login;

import client.domain.usecases.login.LoginUseCase;
import client.domain.usecases.login.RegisterUseCase;
import client.ui.screens.common.UIConstants;
import domain.model.User;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final ObjectProperty<LoginState> state;


    @Inject
    public LoginViewModel(LoginUseCase loginUseCase, RegisterUseCase registerUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        state = new SimpleObjectProperty<>(new LoginState(false, false, null, null));
    }

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void login(String userName, String password) {
        state.setValue(new LoginState(false, true, null, null));

        loginUseCase.login(userName, password)
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(string -> state.setValue(new LoginState(true, false, null, null)))
                        .peekLeft(apiError -> state.setValue(new LoginState(false, false, apiError, null)))
                );
    }

    public void register(String userName, String password) {
        state.setValue(new LoginState(false, true, null, null));

        registerUseCase.register(new User(userName, password))
                .observeOn(Schedulers.single())
                .subscribe(singleResult -> singleResult
                        .peek(userResponse -> state.setValue(new LoginState(false, false, null, UIConstants.REGISTERED_SUCCESSFULLY_PLEASE_LOGIN)))
                        .peekLeft(apiError -> state.setValue(new LoginState(false, false, apiError, null)))
                );
    }
}