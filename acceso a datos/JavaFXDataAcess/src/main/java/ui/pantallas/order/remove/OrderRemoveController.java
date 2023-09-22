package ui.pantallas.order.remove;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import service.OrderService;
import ui.pantallas.common.BaseScreenController;

import java.time.LocalDateTime;

public class OrderRemoveController extends BaseScreenController {

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
}
