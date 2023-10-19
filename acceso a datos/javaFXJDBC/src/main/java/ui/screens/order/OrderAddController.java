package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import model.OrderItem;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderAddController extends BaseScreenController {

    private final CommonOrder commonOrder;

    @Inject
    public OrderAddController(CommonOrder commonOrder) {
        this.commonOrder = commonOrder;
    }


    @FXML
    private TextField txtOrderItemQuantity;
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
    private ComboBox<Integer> comboBoxCustomer;

    @FXML
    private TextField txtTableNumber;

    @FXML
    private ComboBox<String> comboBoxMenuItem;

    @FXML
    private TableView tableOrderItems;
    @FXML
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnItemQuantity;

    public void initialize() {
        //comboBoxCustomer.getItems().addAll();
        //comboBoxMenuItem.getItems().addAll();
        commonOrder.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        commonOrder.initOrderItemList(columnItemName, columnItemQuantity);
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll();//add orderService.getAll().get());
    }

    public void addOrder() {
        if (txtTableNumber.getText().isEmpty() || comboBoxCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        }
        getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
//            int orderId = orderService.getAll().get().stream().mapToInt(Order::getId).max().orElse(0) + 1;
//            Order order = new Order(orderId, comboBoxCustomer.getValue(), Integer.parseInt(txtTableNumber.getText()));
//            if (orderService.save(order).isRight()) {
//                getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
//            } else {
//                getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
//            }

        tableOrders.getItems().clear();
        tableOrders.getItems().addAll();//add orderService.getAll().get());
    }


    public void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
//            if (orderItemService.get(idOrder).isRight()) {
//                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
//            }
        }
    }

    public void addOrderItem() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();

        if (order == null) {
            getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_AN_ORDER, ConstantNormal.ERROR);
        } else if (txtOrderItemQuantity.getText().isEmpty() || txtOrderItemQuantity.getText().isBlank() || comboBoxMenuItem.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        } else if (!txtOrderItemQuantity.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.QUANTITY_MUST_BE_A_NUMBER, ConstantNormal.ERROR);
        } else {
//            if (orderItemService.save(idOrder, new OrderItemXml(comboBoxMenuItem.getValue(), Integer.parseInt(txtOrderItemQuantity.getText()))).isRight()) {
                getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
//            } else {
//                getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
//            }
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll();//orderService.getAll().get()
        tableOrderItems.getItems().clear();
    }
}
