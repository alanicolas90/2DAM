package ui.pantallas.common;

import lombok.Getter;
import ui.pantallas.principal.PrincipalController;


@Getter
public abstract class BasePantallaController {

    private PrincipalController principalController;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() {
    }

}