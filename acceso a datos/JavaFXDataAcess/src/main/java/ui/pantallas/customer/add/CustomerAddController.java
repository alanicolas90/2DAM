package ui.pantallas.customer.add;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Customer;
import service.CustomerService;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.customer.common.CustomerCommon;

public class CustomerAddController extends BasePantallaController {

    @Inject
    private CustomerService customerService;
    @Inject
    private CustomerCommon customerCommon;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> columnId;
    @FXML
    private TableColumn<Customer, String> columnName;
    @FXML
    private TableColumn<Customer, String> columnSurname;
    @FXML
    private TableColumn<Customer, String> columnEmail;
    @FXML
    private TableColumn<Customer, Integer> columnPhone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtName;

    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    public void addCustomer() {
        Customer customer = new Customer();
        if (!txtPhoneNumber.getText().matches("[0-9]+")) {
            getPrincipalController().alertWarning("El teléfono no puede contener letras", "Error");
        } else if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning("No se ha rellenado algún campo", "Error");
        } else {
            int lastIdTable = tableCustomers.getItems().get(tableCustomers.getItems().size() - 1).getId();
            customer.setId(lastIdTable + 1);
            customer.setName(txtName.getText());
            customer.setSurname(txtSurname.getText());
            customer.setEmail(txtEmail.getText());
            customer.setPhone(Integer.parseInt(txtPhoneNumber.getText()));
            tableCustomers.getItems().add(customer);
            getPrincipalController().showInformation("Cliente añadido correctamente", "Información");
        }
    }
}



