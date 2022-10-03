package ui.pantallas.non_alcoholic;


import javafx.fxml.Initializable;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.ResourceBundle;

public class NonAlcoholicDrinksController extends BasePantallaController implements Initializable {

    private final NonAlcohlicViewModel viewModel;

    public NonAlcoholicDrinksController(NonAlcohlicViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}



