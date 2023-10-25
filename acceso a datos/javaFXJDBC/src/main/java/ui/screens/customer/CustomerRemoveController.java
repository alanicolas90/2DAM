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
import ui.screens.common.ConstantsController;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

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
    public void principalLoaded() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    @FXML
    private void selectionTable() {
        Customer selectionTable = tableCustomers.getSelectionModel().getSelectedItem();
        tableOrdersCustomer.getItems().clear();
        if (selectionTable != null) {
            tableOrdersCustomer.getItems().addAll(orderService.getOrdersSpecificCustomer(selectionTable.getId()).getOrElse(Collections.emptyList()));
        }
    }

    @FXML
    private void removeSelected() {
        Customer customer = tableCustomers.getSelectionModel().getSelectedItem();
        if (customer == null) {
            getPrincipalController().alertWarning(ConstantsController.YOU_MUST_SELECT_A_CUSTOMER, ConstantsController.ERROR);
        } else {
            deleteCustomer(customer);
        }

        tableCustomers.getItems().clear();
        tableCustomers.getItems().addAll(customerService.getAll().get());
        tableOrdersCustomer.getItems().clear();
    }


    private void deleteCustomer(Customer customer) {
        if (customerService.delete(customer.getId(), false).isLeft()) {
            askIfWantToDeleteCustomerBecauseHasOrders(customer);
        } else {
            getPrincipalController().showInformation(ConstantsController.CUSTOMER_DELETED_CORRECTLY, ConstantsController.INFORMATION);
        }
    }

    private void askIfWantToDeleteCustomerBecauseHasOrders(Customer customer) {
        boolean wantsToDelete = getPrincipalController().alertDeleteConfirmation(ConstantsController.THERE_ARE_ORDERS_IN_THIS_CUSTOMER_ARE_YOU_SURE_YOU_WANT_TO_DELETE, ConstantsController.WARNING);
        if (wantsToDelete) {
            deleteCustomerWithOrders(customer);
        } else {
            getPrincipalController().alertWarning(ConstantsController.CUSTOMER_NOT_DELETED, ConstantsController.ERROR);
        }
    }


    private void deleteCustomerWithOrders(Customer customer) {
        if (customerService.delete(customer.getId(), true).isLeft()) {
            getPrincipalController().alertWarning(ConstantsController.ORDER_NOT_DELETED, ConstantsController.ERROR);
        } else {
            getPrincipalController().showInformation(ConstantsController.CUSTOMER_DELETED_CORRECTLY, ConstantsController.INFORMATION);
        }
    }


}
