package ui.screens.customer.remove;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRemoveController extends BaseScreenController {
    @Inject
    private CustomerService customerService;
    @Inject
    private CustomerCommon customerCommon;
    @Inject
    private OrderService orderService;

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
    private TableView<Order> tableOrdersCustomer;
    @FXML
    private TableColumn<Customer, Integer> columnIdOrder;
    @FXML
    private TableColumn<Customer, LocalDateTime> columnDate;
    @FXML
    private TableColumn<Customer, Integer> columnCustomerIdOrder;
    @FXML
    private TableColumn<Customer, Integer> columnTableNumber;


    public void initialize() {
        customerCommon.initCustomerTable(columnIdCustomer, columnName, columnSurname, columnEmail, columnPhone, columnDateBirth);
        customerCommon.initOrderTable(columnIdOrder, columnDate, columnCustomerIdOrder, columnTableNumber);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    @FXML
    private void selectionTable() {
        Customer selectionTable = tableCustomers.getSelectionModel().getSelectedItem();
        tableOrdersCustomer.getItems().clear();
        tableOrdersCustomer.getItems().addAll(orderService.getOrdersCustomer(selectionTable.getId()).get());
    }

    public void removeSelected() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
                getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_A_CUSTOMER, ConstantNormal.ERROR);
        } else {
            tableCustomers.getItems().remove(customer);
            tableOrdersCustomer.getItems().clear();
            getPrincipalController().showInformation(ConstantNormal.CUSTOMER_DELETED_CORRECTLY, ConstantNormal.INFORMATION);
        }
    }
}
