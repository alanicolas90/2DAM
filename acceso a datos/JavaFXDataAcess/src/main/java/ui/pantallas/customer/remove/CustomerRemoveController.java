package ui.pantallas.customer.remove;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.Customer;
import service.CustomerService;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.customer.common.CustomerCommon;

public class CustomerRemoveController extends BasePantallaController {

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


    public void initialize() {
        customerCommon.initCustomerTable(columnId, columnName, columnSurname, columnEmail, columnPhone);
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
            getPrincipalController().alertWarning("No se ha seleccionado ningún cliente", "Error");
        } else {
            tableCustomers.getItems().remove(customer);
            getPrincipalController().showInformation("Cliente eliminado correctamente", "Información");
        }
    }
}
