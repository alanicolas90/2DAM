package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Order;
import model.OrderItem;
import service.OrdersService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderRemoveController extends BaseScreenController {

    private final CommonOrder common;
    private final OrdersService ordersService;

    @Inject
    public OrderRemoveController(CommonOrder common, OrdersService ordersService) {
        this.common = common;
        this.ordersService = ordersService;
    }


    @FXML
    private TableView tableOrderItems;
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
        tableOrders.getItems().addAll(ordersService.getAll().get());
    }

    @FXML
    private void deleteOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        if (order == null) {
            getPrincipalController().alertWarning(ConstantsController.YOU_MUST_SELECT_AN_ORDER, ConstantsController.ERROR);
        } else {
            if (ordersService.delete(order.getId()).isRight()) {
                getPrincipalController().showInformation(ConstantsController.ORDER_DELETED_SUCCESSFULLY, ConstantsController.INFORMATION);
            } else {
                getPrincipalController().showInformation(ConstantsController.ORDER_NOT_DELETED, ConstantsController.INFORMATION);
                //TODO. error tiene items por eso no se ha podido borrar / comprobar si quiere borrar
            }
        }

        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(ordersService.getAll().get());
        tableOrderItems.getItems().clear();

    }

    @FXML
    private void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
//            if (orderItemService.get(idOrder).isRight()) {
//                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
//            }
        }
    }
}
