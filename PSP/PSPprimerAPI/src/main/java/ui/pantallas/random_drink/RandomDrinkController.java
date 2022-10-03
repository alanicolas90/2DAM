package ui.pantallas.random_drink;

import domain.modelo.drinks.drink.Drink;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RandomDrinkController extends BasePantallaController implements Initializable {

    @FXML
    public Button bttnRandom;
    @FXML
    public TextArea strInstructionsDrink;
    @FXML
    public TextField strDrink;
    @FXML
    public TextField strGlass;
    @FXML
    public TextField strCategory;
    @FXML
    public TableColumn<Drink, String> strIngr1;
    @FXML
    public TableColumn<Drink, String> strIngr2;
    @FXML
    public TableColumn<Drink, String> strIngr3;
    @FXML
    public TableColumn<Drink, String> strIngr4;
    @FXML
    public TableView<String> ingredientTable;

    private final RandomViewModel viewModel;

    @Inject
    public RandomDrinkController(RandomViewModel viewModel) {
        //No se que meter aqui
        this.viewModel = viewModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        strIngr1.setCellValueFactory(new PropertyValueFactory<>("strIngredient1"));
        strIngr2.setCellValueFactory(new PropertyValueFactory<>("strIngredient2"));
        strIngr3.setCellValueFactory(new PropertyValueFactory<>("strIngredient3"));
        strIngr4.setCellValueFactory(new PropertyValueFactory<>("strIngredient4"));
    }


    @Override
    public void principalCargado() {
        //el principal cargado

    }

    public void getRandomDrink() {
        List<Drink> listDrinks = viewModel.getRandomDrink().getDrinks();
        ingredientTable.getItems().clear();
        strDrink.textProperty().setValue(listDrinks.get(0).getStrDrink());
        strGlass.textProperty().setValue(listDrinks.get(0).getStrGlass());
        strCategory.textProperty().setValue(listDrinks.get(0).getStrCategory());
        strInstructionsDrink.textProperty().setValue(listDrinks.get(0).getStrInstructions());
        //ingredientTable.getItems().addAll(listDrinks.get(0).getStrIngredient1(), listDrinks.get(0).getStrIngredient2(), listDrinks.get(0).getStrIngredient3(), listDrinks.get(0).getStrIngredient4());

    }
}
