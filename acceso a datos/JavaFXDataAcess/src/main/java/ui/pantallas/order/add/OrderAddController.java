package ui.pantallas.order.add;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import service.OrderService;
import ui.pantallas.common.BaseScreenController;

import java.time.LocalDateTime;

public class OrderAddController extends BaseScreenController {

    @Inject
    private OrderService orderService;

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order, Integer> columnId;
    @FXML
    private TableColumn<Order, LocalDateTime> columnDate;
    @FXML
    private TableColumn<Order, Integer> columnCustomerId;
    @FXML
    private TableColumn<Order, Integer> columnTableNumber;


    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtTableNumber;
    @FXML
    private DatePicker dateOrder;

    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }

    public void addOrder() {
        Order order = new Order();
        int id = orderService.getAll().get().size() - 1;
        if (dateOrder.getValue() == null || txtCustomerId.getText().isEmpty() || txtTableNumber.getText().isEmpty()) {
            getPrincipalController().alertWarning("All the fields must be filled", "Error");
        } else if (!txtCustomerId.getText().matches("[-9]")) {
            getPrincipalController().alertWarning("Customer ID cannot contain letters", "Error");
        } else if (txtTableNumber.getText().contains("[-9]")) {
            getPrincipalController().alertWarning("Table number cannot contain letters", "Error");
        } else {
            order.setId(id);
            order.setDate(dateOrder.getValue().atTime(0, 0, 0));
            order.setCustomerId(Integer.parseInt(txtCustomerId.getText()));
            order.setTableNumber(Integer.parseInt(txtTableNumber.getText()));
            tableOrders.getItems().add(order);
        }
    }
}
