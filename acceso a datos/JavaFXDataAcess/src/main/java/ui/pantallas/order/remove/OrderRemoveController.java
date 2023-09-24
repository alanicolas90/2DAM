package ui.pantallas.order.remove;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderItem;
import service.OrderService;
import ui.pantallas.common.BaseScreenController;
import ui.pantallas.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderRemoveController extends BaseScreenController {


    @Inject
    private OrderService orderService;
    @Inject
    private CommonOrder common;
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
    private TableView<OrderItem> tableOrderItems;
    @FXML
    private TableColumn<OrderItem,String> columnItemName;
    @FXML
    private TableColumn<OrderItem,Integer> columnQuantity;
    @FXML
    private TableColumn<OrderItem,Integer> columnPrice;
    @FXML
    private TableColumn<Integer,Integer> columnTotalPrice;

    public void initialize() {
        common.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        common.initOrderItemList(columnItemName, columnQuantity, columnPrice, columnTotalPrice);
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }

    public void deleteOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        if (order == null) {
            getPrincipalController().alertWarning("You must select an order", "Error");
        } else {
            orderService.delete(order);
            tableOrders.getItems().remove(order);
            getPrincipalController().showInformation("Order deleted successfully", "Information");
        }
    }
}
