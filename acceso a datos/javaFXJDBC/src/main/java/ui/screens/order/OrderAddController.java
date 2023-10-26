package ui.screens.order;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderItem;
import service.MenuItemsService;
import service.OrdersService;
import service.TablesServices;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private ComboBox<Integer> comboBoxTableNumber;

    @FXML
    private ComboBox<Integer> comboBoxCustomer;

    @FXML
    private ComboBox<String> comboBoxMenuItem;

    @FXML
    private TableView<OrderItem> tableOrderItems;
    @FXML
    private TableColumn<OrderItem, Integer> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnItemQuantity;

    private List<OrderItem> orderItems;


    public void initialize() {
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));
        columnItemQuantity.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.QUANTITY));
        comboBoxCustomer.setDisable(true);

        comboBoxTableNumber.getItems().addAll(tablesServices.getAllTableNumbers());
        comboBoxMenuItem.getItems().addAll(menuItemsService.getAllNames().get());
        orderItems = new ArrayList<>();
    }

    @Override
    public void principalLoaded() {
        comboBoxCustomer.setValue(getPrincipalController().getIdUserLogged());

    }

    @FXML
    private void addOrder() {

        if (comboBoxTableNumber.getValue() == null) {
            getPrincipalController().alertWarning(ConstantsController.TABLE_NUMBER_MUST_BE_A_NUMBER, ConstantsController.ERROR);
        } else {
            if (orderItems.isEmpty()) {
                boolean confirmation = getPrincipalController().alertDeleteConfirmation("Are you sure you want to create a empty order?", "Create order");
                if (confirmation) {
                    tryAddOrder();
                }
            } else {
                tryAddOrder();
                orderItems.clear();
            }
            tableOrderItems.getItems().clear();
            tableOrderItems.getItems().addAll(orderItems);
        }

    }

    @FXML
    private void addOrderItem() {

        if (txtOrderItemQuantity.getText().isEmpty() || txtOrderItemQuantity.getText().isBlank() || comboBoxMenuItem.getValue() == null) {
            getPrincipalController().alertWarning(ConstantsController.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantsController.ERROR);
        } else if (!txtOrderItemQuantity.getText().matches(ConstantsController.CONTAINS_LETTERS)) {
            getPrincipalController().alertWarning(ConstantsController.QUANTITY_MUST_BE_A_NUMBER, ConstantsController.ERROR);
        } else {
            orderItems.add(new OrderItem(menuItemsService.getMenuItemIdByName(comboBoxMenuItem.getValue()), Integer.parseInt(txtOrderItemQuantity.getText())));
            getPrincipalController().showInformation(ConstantsController.ORDER_ADDED_SUCCESSFULLY, ConstantsController.SUCCESS);

        }
        tableOrderItems.getItems().clear();
        tableOrderItems.getItems().addAll(orderItems);
    }

    private void tryAddOrder() {
        int tableNumber = comboBoxTableNumber.getValue();
        Order order = new Order(LocalDateTime.now(), getPrincipalController().getIdUserLogged(), tableNumber, orderItems);

        if (ordersService.add(order).isRight()) {
            getPrincipalController().showInformation(ConstantsController.ORDER_ADDED_SUCCESSFULLY, ConstantsController.SUCCESS);
        } else {
            getPrincipalController().alertWarning(ConstantsController.ERROR_ADDING_ORDER, ConstantsController.ERROR);
        }
    }

    @FXML
    private void removeOrderItem() {
        if (tableOrderItems.getSelectionModel().getSelectedItem() == null) {
            getPrincipalController().alertWarning("You must select a order item t delete it", ConstantsController.ERROR);
        } else {
            orderItems.remove(tableOrderItems.getSelectionModel().getSelectedItem());
            getPrincipalController().showInformation("Order item deleted successfully", ConstantsController.SUCCESS);
            tableOrderItems.getItems().clear();
            tableOrderItems.getItems().addAll(orderItems);
        }
    }
}
