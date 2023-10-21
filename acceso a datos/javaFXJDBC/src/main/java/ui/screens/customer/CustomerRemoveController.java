package ui.screens.customer;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrdersService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerRemoveController extends BaseScreenController {

    private final CustomerService customerService;
    private final CustomerCommon customerCommon;
    private final OrdersService orderService;

    @Inject
    public CustomerRemoveController(CustomerCommon customerCommon, CustomerService customerService, OrdersService orderService) {
        this.customerCommon = customerCommon;
        this.customerService = customerService;
        this.orderService = orderService;
    }

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
        if (selectionTable != null && (orderService.getOrdersSpecificCustomer(selectionTable.getId()).isRight())) {
            tableOrdersCustomer.getItems().addAll(orderService.getOrdersSpecificCustomer(selectionTable.getId()).get());

        }
    }

    @FXML
    public void removeSelected() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_A_CUSTOMER, ConstantNormal.ERROR);
        } else {
            deleteCustomer(customer);
        }

        tableCustomers.getItems().clear();
        tableCustomers.getItems().addAll(customerService.getAll().get());
        tableOrdersCustomer.getItems().clear();
    }

    private void deleteCustomer(Customer customer) {
        if (customerService.delete(customer.getId(), false).isLeft()) {
            boolean wantsToDelete = getPrincipalController().alertDeleteConfirmation("There are orders in this customer. Are you sure you want to delete?", ConstantNormal.WARNING);
            if (wantsToDelete) {
                if (customerService.delete(customer.getId(), true).isLeft()) {
                    getPrincipalController().alertWarning(ConstantNormal.ORDER_NOT_DELETED, ConstantNormal.ERROR);
                } else {
                    getPrincipalController().showInformation(ConstantNormal.CUSTOMER_DELETED_CORRECTLY, ConstantNormal.INFORMATION);
                }
            } else {
                getPrincipalController().alertWarning(ConstantNormal.CUSTOMER_NOT_DELETED, ConstantNormal.ERROR);
            }
        } else {
            getPrincipalController().showInformation(ConstantNormal.CUSTOMER_DELETED_CORRECTLY, ConstantNormal.INFORMATION);
        }
    }


}
