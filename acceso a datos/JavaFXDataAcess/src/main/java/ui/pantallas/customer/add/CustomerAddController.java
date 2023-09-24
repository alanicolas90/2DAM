package ui.pantallas.customer.add;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import service.CustomerService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.customer.common.CustomerCommon;

import java.time.LocalDate;

public class CustomerAddController extends BaseScreenController {

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
    public TableColumn<Customer, LocalDate> columnDateBirth;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker dateOfBirthCustomer;

    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone, columnDateBirth);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    public void addCustomer() {
        Customer customer = new Customer();
        if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning("There are empty fields", "Error");
        } else if (!txtPhoneNumber.getText().matches("\\d+")) {
            getPrincipalController().alertWarning("Phone number cannot contain letters", "Error");
        } else {
            int lastIdTable = tableCustomers.getItems().get(tableCustomers.getItems().size() - 1).getId();
            customer.setId(lastIdTable + 1);
            customerCommon.setsNameSurnameEmailPhoneBirth(customer, txtName, txtSurname, txtEmail, txtPhoneNumber, dateOfBirthCustomer);
            tableCustomers.getItems().add(customer);
            getPrincipalController().showInformation("Client added correctly", "Information");
        }
    }


}



