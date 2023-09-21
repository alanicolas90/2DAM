package ui.pantallas.customer.update;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import service.CustomerService;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.customer.common.CustomerCommon;

public class CustomerUpdateController extends BasePantallaController {

    @Inject
    private CustomerService customerService;
    @Inject
    private CustomerCommon customerCommon;

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
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

    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    @FXML
    private void selectionTable() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        txtName.setText(customer.getName());
        txtSurname.setText(customer.getSurname());
        txtEmail.setText(customer.getEmail());
        txtPhoneNumber.setText(String.valueOf(customer.getPhone()));
    }

    @FXML
    private void updateCustomer() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            getPrincipalController().alertWarning("No se ha seleccionado ningún cliente", "Error");
        } else if (txtPhoneNumber.getText().matches("[-9]")) {
            getPrincipalController().alertWarning("El teléfono no puede contener letras", "Error");
        } else if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning("No se ha rellenado algún campo", "Error");
        } else {
            customer.setName(txtName.getText());
            customer.setSurname(txtSurname.getText());
            customer.setEmail(txtEmail.getText());
            customer.setPhone(Integer.parseInt(txtPhoneNumber.getText()));
            getPrincipalController().showInformation("Cliente actualizado correctamente", "Información");
        }
    }
}
