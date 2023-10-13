package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import model.OrderItem;
import model.xml.OrderItemXml;
import service.OrderItemService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRemoveController extends BaseScreenController {

    private final OrderService orderService;
    private final CommonOrder common;

    private final OrderItemService orderItemService;

    @Inject
    public OrderRemoveController(OrderService orderService, CommonOrder common, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.common = common;
        this.orderItemService = orderItemService;
    }


    @FXML
    private TableView<OrderItemXml> tableOrderItems;
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
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnQuantity;


    public void initialize() {
        common.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        common.initOrderItemList(columnItemName, columnQuantity);
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }

    public void deleteOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
        if (order == null) {
            getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_AN_ORDER, ConstantNormal.ERROR);
        } else if (orderItemService.get(idOrder).isRight()) {
            if (getPrincipalController().alertDeleteConfirmation("There are order Items, are you sure youw ant to delete?", "Warning")) {
                if (orderService.delete(List.of(order.getId())).isRight()) {
                    orderItemService.delete(idOrder);
                    getPrincipalController().showInformation(ConstantNormal.ORDER_DELETED_SUCCESSFULLY, ConstantNormal.INFORMATION);
                }
            } else {
                getPrincipalController().showInformation("Order not deleted", ConstantNormal.INFORMATION);
            }
        } else {
            orderService.delete(List.of(order.getId()));
            getPrincipalController().showInformation(ConstantNormal.ORDER_DELETED_SUCCESSFULLY, ConstantNormal.INFORMATION);
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(orderService.getAll().get());
        tableOrderItems.getItems().clear();

    }

    public void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
            if (orderItemService.get(idOrder).isRight()) {
                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
            }
        }
    }
}
