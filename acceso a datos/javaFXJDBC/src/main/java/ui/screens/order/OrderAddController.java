package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import model.OrderItem;
import service.CustomerService;
import service.MenuItemsService;
import service.OrdersService;
import service.TablesServices;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDate;
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

    public void addOrder() {
        if (!txtTableNumber.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning("Table number must be a number", ConstantNormal.ERROR);
        } else{
            int tableNumber = Integer.parseInt(txtTableNumber.getText());
            if(tablesServices.tableExists(tableNumber)){
                if(ordersService.add(LocalDateTime.now(),getPrincipalController().getIdUserLogged(),tableNumber).isRight()){
                    getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
                }else{
                    getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
                }
            }else{
                getPrincipalController().alertWarning("Table number does not exist", ConstantNormal.ERROR);
            }
        }

        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
    }


    public void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            //int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
//            if (orderItemService.get(idOrder).isRight()) {
//                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
//            }
        }
    }

    public void addOrderItem() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        //int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();

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
        tableOrders.getItems().addAll(ordersService.get(getPrincipalController().getIdUserLogged()).get());
        tableOrderItems.getItems().clear();
    }
}
