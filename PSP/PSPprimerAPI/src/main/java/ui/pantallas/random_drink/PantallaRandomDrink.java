package ui.pantallas.random_drink;

import domain.modelo.drinks.drink.Drink;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class PantallaRandomDrink extends BasePantallaController  implements Initializable {

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


    public PantallaRandomDrink() {
        //No se que meter aqui

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

    public void getRandomDrink(ActionEvent actionEvent) {
        ingredientTable.getItems().clear();

    }
}
