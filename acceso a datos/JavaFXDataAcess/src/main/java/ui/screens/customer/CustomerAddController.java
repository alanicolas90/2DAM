package ui.screens.customer;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Customer;
import service.CustomerService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;

public class CustomerAddController extends BaseScreenController {

    @Inject
    public CustomerAddController(CustomerService customerService, CustomerCommon customerCommon) {
        this.customerService = customerService;
        this.customerCommon = customerCommon;
    }

    private final CustomerService customerService;
    private final CustomerCommon customerCommon;

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
        if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() || dateOfBirthCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.THERE_ARE_EMPTY_FIELDS, ConstantNormal.ERROR);
        } else if (!txtPhoneNumber.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.PHONE_NUMBER_CANNOT_CONTAIN_LETTERS, ConstantNormal.ERROR);
        } else {
            int size = tableCustomers.getItems().size();
            int lastIdTable = tableCustomers.getItems().get(size - 1).getId();
            Customer customer = new Customer(lastIdTable + 1, txtName.getText(), txtSurname.getText(), txtEmail.getText(), Integer.parseInt(txtPhoneNumber.getText()), dateOfBirthCustomer.getValue());
            tableCustomers.getItems().add(customer);
            getPrincipalController().showInformation(ConstantNormal.CLIENT_ADDED_CORRECTLY, ConstantNormal.INFORMATION);

        }
    }
}



