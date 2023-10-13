package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import model.OrderItem;
import model.xml.OrderItemXml;
import service.CustomerService;
import service.MenuItemService;
import service.OrderItemService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderAddController extends BaseScreenController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final OrderItemService orderItemService;
    private final CommonOrder commonOrder;
    private final MenuItemService menuItemService;

    @Inject
    public OrderAddController(OrderService orderService, CustomerService customerService, OrderItemService orderItemService, CommonOrder commonOrder, MenuItemService menuItemService) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.orderItemService = orderItemService;
        this.commonOrder = commonOrder;
        this.menuItemService = menuItemService;
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
    private TableView<OrderItemXml> tableOrderItems;
    @FXML
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnItemQuantity;

    public void initialize() {
        comboBoxCustomer.getItems().addAll(customerService.getAllIds().get());
        comboBoxMenuItem.getItems().addAll(menuItemService.getAll().get());
        commonOrder.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        commonOrder.initOrderItemList(columnItemName, columnItemQuantity);
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }

    public void addOrder() {
        if (txtTableNumber.getText().isEmpty() || comboBoxCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        } else {
            int orderId = orderService.getAll().get().stream().mapToInt(Order::getId).max().orElse(0) + 1;
            Order order = new Order(orderId, comboBoxCustomer.getValue(), Integer.parseInt(txtTableNumber.getText()));
            if (orderService.save(order).isRight()) {
                getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
            } else {
                getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
            }
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(orderService.getAll().get());
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

    public void addOrderItem() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();

        if (order == null) {
            getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_AN_ORDER, ConstantNormal.ERROR);
        } else if (txtOrderItemQuantity.getText().isEmpty() || txtOrderItemQuantity.getText().isBlank() || comboBoxMenuItem.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        } else if (!txtOrderItemQuantity.getText().matches("\\d+")) {
            getPrincipalController().alertWarning("Quantity must be a number", ConstantNormal.ERROR);
        } else {
            if (orderItemService.save(idOrder, new OrderItemXml(comboBoxMenuItem.getValue(), Integer.parseInt(txtOrderItemQuantity.getText()))).isRight()) {
                getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
            } else {
                getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
            }
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(orderService.getAll().get());
        tableOrderItems.getItems().clear();
    }
}
