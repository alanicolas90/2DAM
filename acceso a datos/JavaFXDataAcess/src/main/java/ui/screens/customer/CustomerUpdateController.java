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

public class CustomerUpdateController extends BaseScreenController {

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
    private DatePicker dateOfBirthCustomer;
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

    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone, columnDateBirth);
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
        dateOfBirthCustomer.setValue(customer.getBirthDate());
    }

    @FXML
    private void updateCustomer() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            getPrincipalController().alertWarning(ConstantNormal.NO_CLIENT_WAS_SELECTED, ConstantNormal.ERROR);
        } else if (!txtPhoneNumber.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.PHONE_NUMBER_CANNOT_CONTAIN_LETTERS, ConstantNormal.ERROR);
        } else if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning(ConstantNormal.THERE_ARE_MISSING_FIELDS, ConstantNormal.ERROR);
        } else {
            customerCommon.setsNameSurnameEmailPhoneBirth(customer, txtName, txtSurname, txtEmail, txtPhoneNumber, dateOfBirthCustomer);
            getPrincipalController().showInformation(ConstantNormal.CLIENT_GOT_UPDATED_CORRECTLY, ConstantNormal.INFORMATION);
        }
    }
}
