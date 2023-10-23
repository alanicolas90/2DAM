package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import model.OrderItem;
import service.MenuItemsService;
import service.OrdersService;
import service.TablesServices;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderAddController extends BaseScreenController {

    private final CommonOrder commonOrder;
    private final OrdersService ordersService;
    private final TablesServices tablesServices;
    private final MenuItemsService menuItemsService;

    @Inject
    public OrderAddController(CommonOrder commonOrder, OrdersService ordersService, TablesServices tablesServices, MenuItemsService menuItemsService) {
        this.commonOrder = commonOrder;
        this.ordersService = ordersService;
        this.tablesServices = tablesServices;
        this.menuItemsService = menuItemsService;
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
    private TableView<String> tableOrderItems;
    @FXML
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnItemQuantity;


    public void initialize() {
        comboBoxCustomer.setDisable(true);
        comboBoxMenuItem.getItems().addAll();
        commonOrder.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        commonOrder.initOrderItemList(columnItemName, columnItemQuantity);
    }

    @Override
    public void principalCargado() {
        comboBoxCustomer.setValue(getPrincipalController().getIdUserLogged());
        comboBoxMenuItem.getItems().addAll(menuItemsService.getAllNames().get());
        if (ordersService.get(getPrincipalController().getIdUserLogged()).isRight()) {
            tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
        }
    }

    @FXML
    private void addOrder() {
        if (!txtTableNumber.getText().matches(ConstantsController.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantsController.TABLE_NUMBER_MUST_BE_A_NUMBER, ConstantsController.ERROR);
        } else {
            tryAddOrder();
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
    }


    @FXML
    private void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            //TODO get order items with id order
        }
    }

    @FXML
    private void addOrderItem() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();

        if (order == null) {
            getPrincipalController().alertWarning(ConstantsController.YOU_MUST_SELECT_AN_ORDER, ConstantsController.ERROR);
        } else if (txtOrderItemQuantity.getText().isEmpty() || txtOrderItemQuantity.getText().isBlank() || comboBoxMenuItem.getValue() == null) {
            getPrincipalController().alertWarning(ConstantsController.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantsController.ERROR);
        } else if (!txtOrderItemQuantity.getText().matches(ConstantsController.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantsController.QUANTITY_MUST_BE_A_NUMBER, ConstantsController.ERROR);
        } else {
            getPrincipalController().showInformation(ConstantsController.ORDER_ADDED_SUCCESSFULLY, ConstantsController.SUCCESS);
            //TODO add order item
        }
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
        tableOrderItems.getItems().clear();
    }

    private void tryAddOrder() {
        int tableNumber = Integer.parseInt(txtTableNumber.getText());
        if (tablesServices.tableExists(tableNumber)) {
            addOrder(tableNumber);
        } else {
            getPrincipalController().alertWarning(ConstantsController.TABLE_NUMBER_DOES_NOT_EXIST, ConstantsController.ERROR);
        }
    }

    private void addOrder(int tableNumber) {
        if (ordersService.add(LocalDateTime.now(), getPrincipalController().getIdUserLogged(), tableNumber).isRight()) {
            getPrincipalController().showInformation(ConstantsController.ORDER_ADDED_SUCCESSFULLY, ConstantsController.SUCCESS);
        } else {
            getPrincipalController().alertWarning(ConstantsController.ERROR_ADDING_ORDER, ConstantsController.ERROR);
        }
    }
}
