package ui.pantallas.customer.add;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import ui.pantallas.common.BasePantallaController;

public class CustomerAddController extends BasePantallaController {

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtName;

}
