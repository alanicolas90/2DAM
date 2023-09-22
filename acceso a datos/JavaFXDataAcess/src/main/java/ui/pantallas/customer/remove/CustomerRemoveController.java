package ui.pantallas.customer.remove;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;
import service.CustomerService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.customer.common.CustomerCommon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRemoveController extends BaseScreenController {
    @Inject
    private CustomerService customerService;
    @Inject
    private CustomerCommon customerCommon;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> columnIdCustomer;
    @FXML
    private TableColumn<Customer, String> columnName;
    @FXML
    private TableColumn<Customer, String> columnSurname;
    @FXML
    private TableColumn<Customer, String> columnEmail;
    @FXML
    private TableColumn<Customer, Integer> columnPhone;
    @FXML
    private TableColumn<Customer, LocalDate> columnDateBirth;
    @FXML
    private TableColumn<Customer, Integer> columnIdOrder;
    @FXML
    private TableColumn<Customer, LocalDateTime> columnDate;
    @FXML
    private TableColumn<Customer,Integer> columnCustomerIdOrder;
    @FXML
    private TableColumn<Customer,Integer> columnTableNumber;




    public void initialize() {
        customerCommon.initCustomerTable(columnIdCustomer, columnName, columnSurname, columnEmail, columnPhone,columnDateBirth);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    @FXML
    private Customer selectionTable() {
        return tableCustomers.getSelectionModel().getSelectedItem();
    }

    public void removeSelected() {
        Customer customer = selectionTable();
        if (customer == null) {
            getPrincipalController().alertWarning("You must select a customer", "Error");
        } else {
            tableCustomers.getItems().remove(customer);
            getPrincipalController().showInformation("Customer deleted correctly", "Information");
        }
    }
}
