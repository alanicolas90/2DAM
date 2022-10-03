package ui.pantallas.non_alcoholic;

import dao.DaoDrinks;
import jakarta.inject.Inject;

public class NonAlcohlicViewModel {

    private final DaoDrinks daoDrinks;

    @Inject
    public NonAlcohlicViewModel(DaoDrinks daoDrinks) {
        this.daoDrinks = daoDrinks;
    }
}
