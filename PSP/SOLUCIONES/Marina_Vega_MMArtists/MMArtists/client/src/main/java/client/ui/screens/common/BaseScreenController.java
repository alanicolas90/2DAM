package client.ui.screens.common;

import client.ui.screens.principal.PrincipalController;
import lombok.Getter;

@Getter
public abstract class BaseScreenController {

    private PrincipalController principalController;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public abstract void principalLoaded();
}