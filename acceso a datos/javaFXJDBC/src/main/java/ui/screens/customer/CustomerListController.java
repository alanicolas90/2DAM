package ui.screens.customer;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;
import service.CustomerService;
import ui.screens.common.BaseScreenController;
import ui.screens.customer.common.CustomerCommon;

import java.time.LocalDate;

public class CustomerListController extends BaseScreenController {

    private final CustomerCommon customerCommon;
    private final CustomerService customerService;

    @Inject
    public CustomerListController(CustomerCommon customerCommon, CustomerService customerService) {
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
    private TableColumn<Customer, LocalDate> columnDateBirth;

    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone, columnDateBirth);
    }

    @Override
    public void principalCargado() {
        tableCustomers.getItems().addAll(customerService.getAll().get());
    }

    public void refreshTable() {
        tableCustomers.getItems().clear();
        tableCustomers.getItems().addAll(customerService.getAll().get());
        getPrincipalController().showInformation("Se ha actualizado la tabla correctamente", "Info");
    }
}

