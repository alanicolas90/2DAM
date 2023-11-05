package ui.screens.order;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ErrorC;
import model.Order;
import model.OrderItem;
import service.OrderItemsService;
import service.OrdersService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;
import java.util.List;

public class OrderRemoveController extends BaseScreenController {

    private final CommonOrder common;
    private final OrdersService ordersService;

    private final OrderItemsService orderItemService;

    @Inject
    public OrderRemoveController(CommonOrder common, OrdersService ordersService, OrderItemsService orderItemService) {
        this.common = common;
        this.ordersService = ordersService;
        this.orderItemService = orderItemService;
    }


    @FXML
    private TableView<OrderItem> tableOrderItems;
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
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.QUANTITY));
    }

    @Override
    public void principalLoaded() {
        Either<ErrorC, List<Order>> eitherGetOrders = ordersService.getAll();
        if (eitherGetOrders.isRight()) {
            tableOrders.getItems().addAll(eitherGetOrders.get());
        }
    }

    @FXML
    private void deleteOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        if (order == null) {
            getPrincipalController().alertWarning(ConstantsController.YOU_MUST_SELECT_AN_ORDER, ConstantsController.ERROR);
        } else {
            deleteOrder(order);
        }

        tableOrders.getItems().clear();
        if (ordersService.getAll().isRight()) {
            tableOrders.getItems().addAll(ordersService.getAll().get());
        }
        tableOrderItems.getItems().clear();

    }

    private void deleteOrder(Order order) {
        if (ordersService.delete(order.getId(), false).isLeft()) {
            askIfUserWantsToDeleteOrdersWithItems(order);
        } else {
            getPrincipalController().showInformation(ConstantsController.ORDER_DELETED_SUCCESSFULLY, ConstantsController.INFORMATION);
        }
    }

    private void askIfUserWantsToDeleteOrdersWithItems(Order order) {
        boolean wantsToDelete = getPrincipalController().alertDeleteConfirmation("This order has order items. Are you sure you want to delete it?", "Confirmation delete");
        if (wantsToDelete) {
            deleteOrderWithOrderItems(order);
        } else {
            getPrincipalController().showInformation(ConstantsController.ORDER_NOT_DELETED, ConstantsController.INFORMATION);
        }
    }

    private void deleteOrderWithOrderItems(Order order) {
        if (ordersService.delete(order.getId(), true).isLeft())
            getPrincipalController().showInformation(ConstantsController.ORDER_NOT_DELETED, ConstantsController.INFORMATION);
        else
            getPrincipalController().showInformation(ConstantsController.ORDER_DELETED_SUCCESSFULLY, ConstantsController.INFORMATION);
    }

    @FXML
    private void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
            if (orderItemService.get(idOrder).isRight()) {
                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
            }
        }
    }
}
