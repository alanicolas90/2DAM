package ui.pantallas.alcoholic;


import domain.usecases.DrinksUseCase;
import jakarta.inject.Inject;

public class AlcoholicDrinksViewModel {

    private final DrinksUseCase drinksUseCase;

    @Inject
    public AlcoholicDrinksViewModel(DrinksUseCase drinksUseCase) {
        this.drinksUseCase = drinksUseCase;
    }



}
