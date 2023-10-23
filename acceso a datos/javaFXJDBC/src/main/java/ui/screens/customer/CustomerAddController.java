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

public class CustomerAddController extends BaseScreenController {
    private final CustomerCommon customerCommon;
    private final CustomerService customerService;

    @Inject
    public CustomerAddController(CustomerCommon customerCommon, CustomerService customerService) {

        this.customerCommon = customerCommon;
        this.customerService = customerService;
    }

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

    @FXML
    private void addCustomer() {
        if (txtName.getText().isEmpty() || txtSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() || dateOfBirthCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantsController.THERE_ARE_EMPTY_FIELDS, ConstantsController.ERROR);
        } else if (!txtPhoneNumber.getText().matches(ConstantsController.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantsController.PHONE_NUMBER_CANNOT_CONTAIN_LETTERS, ConstantsController.ERROR);
        } else {
            if (customerService.saveAutoIncrementalID(txtName.getText(), txtSurname.getText(), txtEmail.getText(), Integer.parseInt(txtPhoneNumber.getText()), dateOfBirthCustomer.getValue()) <= 1) {
                getPrincipalController().alertWarning(ConstantsController.ERROR, ConstantsController.ERROR);
            } else {
                tableCustomers.getItems().clear();
                tableCustomers.getItems().addAll(customerService.getAll().get());
                getPrincipalController().showInformation(ConstantsController.CLIENT_ADDED_CORRECTLY, ConstantsController.INFORMATION);
            }
        }
    }
}



