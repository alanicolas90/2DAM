package ui.screens.order.list;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import model.OrderItem;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderListController extends BaseScreenController {

    @Inject
    private OrderService orderService;
    @Inject
    private CommonOrder commonOrder;
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
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnQuantity;
    @FXML
    private TableColumn<OrderItem, Integer> columnPrice;
    @FXML
    private TableColumn<Integer,Integer> columnTotalPrice;

    public void initialize(){
        commonOrder.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        commonOrder.initOrderItemList(columnItemName, columnQuantity, columnPrice, columnTotalPrice);
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());

    }

    public void orderSelected() {
        tableOrderItems.getItems().clear();
    }
}

