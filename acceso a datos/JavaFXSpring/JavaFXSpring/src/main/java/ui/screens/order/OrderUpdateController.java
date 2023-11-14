package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.MenuItem;
import model.Order;
import service.MenuItemsService;
import service.OrdersService;
import service.TablesServices;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantsController;
import ui.screens.order.common.CommonOrder;
import ui.screens.order.model.MenuItemTable;

import java.time.LocalDateTime;

public class OrderUpdateController extends BaseScreenController {

    private final CommonOrder common;
    private final OrdersService ordersService;
    private final TablesServices tablesServices;
    private final MenuItemsService menuItemsService;
    @Inject
    public OrderUpdateController(CommonOrder common, OrdersService ordersService, TablesServices tablesServices,MenuItemsService menuItemsService) {
        this.common = common;
        this.ordersService = ordersService;
        this.tablesServices = tablesServices;
        this.menuItemsService = menuItemsService;
    }


    @FXML
    private TableColumn<MenuItem,Integer> columnIdOrderItem;
    @FXML
    private TableColumn<MenuItem, String> columnNameOrderItem;
    @FXML
    private TableColumn<MenuItem,Double> columnPriceOrderItem;
    @FXML
    private TableColumn<MenuItem, Integer> columnQuantityOrderItem;
    @FXML
    private TextField txtQuantityMenuItem;
    @FXML
    private ComboBox<String> comboBoxMenuItem;
    @FXML
    private TableView<MenuItemTable> tableOrderItems;
    @FXML
    private TextField txtCustomerId;
    @FXML
    private TextField txtTableNumber;
    @FXML
    private DatePicker dateOfBirthCustomer;
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
        common.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        columnIdOrderItem.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ORDER_ID));
        columnNameOrderItem.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ITEM_NAME));
        columnPriceOrderItem.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.PRICE));
        columnQuantityOrderItem.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.QUANTITY));
        txtCustomerId.setDisable(true);
    }

    @Override
    public void principalLoaded() {
        txtCustomerId.setText(String.valueOf(getPrincipalController().getIdUserLogged()));
        comboBoxMenuItem.getItems().addAll(menuItemsService.getAllNames().get());
        loadTable();
    }

    @FXML
    private void selectionTableOrders() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        tableOrderItems.getItems().clear();
        if (order != null) {
            dateOfBirthCustomer.setValue(order.getDate().toLocalDate());
            txtCustomerId.setText(String.valueOf(order.getCustomerId()));
            txtTableNumber.setText(String.valueOf(order.getTableNumber()));
            tableOrderItems.getItems().addAll(menuItemsService.getAllMenuItems(order.getId()).get());
        }
    }

    @FXML
    private void updateOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        if (order == null) {
            getPrincipalController().alertWarning(ConstantsController.YOU_MUST_SELECT_AN_ORDER, ConstantsController.ERROR);
        } else if (txtTableNumber.getText().isEmpty() || dateOfBirthCustomer.getValue().toString().isBlank()) {
            getPrincipalController().alertWarning(ConstantsController.THERE_ARE_EMPTY_FIELDS, ConstantsController.ERROR);
        } else if (!txtTableNumber.getText().matches(ConstantsController.CONTAINS_LETTERS)) {
            getPrincipalController().alertWarning(ConstantsController.TABLE_NUMBER_MUST_BE_A_NUMBER, ConstantsController.ERROR);
        } else {

            if (!tablesServices.tableExists(Integer.parseInt(txtTableNumber.getText()))) {
                getPrincipalController().alertWarning("Table number does not exist, please write one that exists", ConstantsController.ERROR);
            } else {
                Order orderUpdate = new Order(order.getId(),
                        dateOfBirthCustomer.getValue().atTime(0, 0, 0), order.getCustomerId(),
                        Integer.parseInt(txtTableNumber.getText()));

                if (ordersService.update(orderUpdate).isLeft()) {
                    getPrincipalController().alertWarning("Error updating order", ConstantsController.ERROR);
                } else {
                    getPrincipalController().showInformation("Order updated successfully", ConstantsController.INFORMATION);
                }

            }
        }
        loadTable();
    }

    private void loadTable() {
        if (getPrincipalController().getIdUserLogged() < 0) {
            tableOrders.getItems().clear();
            if (ordersService.getAll().isRight()) {
                tableOrders.getItems().addAll(ordersService.getAll().get());
            }
        } else {
            tableOrders.getItems().clear();
            if (ordersService.getAll().isRight()) {
                tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
            }
        }
    }

}
