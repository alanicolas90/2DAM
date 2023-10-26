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
import ui.screens.common.ConstantsController;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;

public class CustomerUpdateController extends BaseScreenController {

    private final CustomerCommon customerCommon;

    private final CustomerService customerService;


    @Inject
    public CustomerUpdateController(CustomerCommon customerCommon, CustomerService customerService) {
        this.customerCommon = customerCommon;
        this.customerService = customerService;
    }


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
    public void principalLoaded() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    @FXML
    private void selectionTable() {
        if (tableCustomers.getSelectionModel().getSelectedItem() != null) {
            Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
            txtName.setText(customer.getName());
            txtSurname.setText(customer.getSurname());
            txtEmail.setText(customer.getEmail());
            txtPhoneNumber.setText(String.valueOf(customer.getPhone()));
            dateOfBirthCustomer.setValue(customer.getBirthDate());
        }
    }

    @FXML
    private void updateCustomer() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            getPrincipalController().alertWarning(ConstantsController.NO_CLIENT_WAS_SELECTED, ConstantsController.ERROR);
        } else if (!txtPhoneNumber.getText().matches(ConstantsController.CONTAINS_LETTERS)) {
            getPrincipalController().alertWarning(ConstantsController.PHONE_NUMBER_CANNOT_CONTAIN_LETTERS, ConstantsController.ERROR);
        } else if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning(ConstantsController.THERE_ARE_MISSING_FIELDS, ConstantsController.ERROR);
        } else if (txtPhoneNumber.getText().length() != 9) {
            getPrincipalController().alertWarning(ConstantsController.PHONE_NUMBER_MUST_HAVE_9_DIGITS, ConstantsController.ERROR);
        } else {
            updateCustomer(customer);
        }
        tableCustomers.getItems().clear();
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }


    private void updateCustomer(Customer customer) {
        Customer customerUpdated = new Customer(customer.getId(), txtName.getText(), txtSurname.getText(), txtEmail.getText(), Integer.parseInt(txtPhoneNumber.getText()), dateOfBirthCustomer.getValue());
        if (customerService.update(customerUpdated).isLeft()) {
            getPrincipalController().alertWarning(ConstantsController.THERE_WERE_NO_CHANGE, ConstantsController.ERROR);
        } else {
            getPrincipalController().showInformation(ConstantsController.CLIENT_GOT_UPDATED_CORRECTLY, ConstantsController.INFORMATION);
        }
    }
}
