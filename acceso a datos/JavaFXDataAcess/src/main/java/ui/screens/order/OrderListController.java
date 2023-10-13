package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import model.OrderItem;
import model.xml.OrderItemXml;
import service.CustomerService;
import service.OrderItemService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderListController extends BaseScreenController {

    private final OrderService orderService;
    private final CustomerService customerService;
    private final CommonOrder commonOrder;
    private final OrderItemService orderItemService;

    @Inject
    public OrderListController(OrderService orderService, CustomerService customerService, CommonOrder commonOrder, OrderItemService orderItemService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.commonOrder = commonOrder;
        this.orderItemService = orderItemService;
    }

    @FXML
    private DatePicker datePicker;
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
    private TableView<OrderItemXml> tableOrderItems;
    @FXML
    private TableColumn<OrderItem, String> columnItemName;
    @FXML
    private TableColumn<OrderItem, Integer> columnQuantity;
    @FXML
    private TextField txtCustomerName;

    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private ComboBox<Integer> comboBoxCustomer;

    public void initialize() {
        commonOrder.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        commonOrder.initOrderItemList(columnItemName, columnQuantity);

        comboBoxCustomer.getItems().addAll(customerService.getAllIds().get());
        filterComboBox.getItems().addAll("Date", "Customer", "None");
        comboBoxCustomer.setDisable(true);
        datePicker.setDisable(true);

    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
        filterComboBox.setOnAction(event -> selectedFilter());
        comboBoxCustomer.setOnAction(event -> selectedBox());
        datePicker.setOnAction(event -> selectedDate());
    }

    public void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idCustomer = tableOrders.getSelectionModel().getSelectedItem().getCustomerId();
            txtCustomerName.setText(customerService.get(idCustomer).get().getName());
            int idOrder = tableOrders.getSelectionModel().getSelectedItem().getId();
            if (orderItemService.get(idOrder).isRight()){
                tableOrderItems.getItems().addAll(orderItemService.get(idOrder).get());
            }
        }
    }

    public void selectedBox() {
        if (comboBoxCustomer.getValue() != null) {
            tableOrders.getItems().clear();
            if (orderService.getOrdersCustomer(comboBoxCustomer.getValue()).isRight()) {
                tableOrders.getItems().addAll(orderService.getOrdersCustomer(comboBoxCustomer.getValue()).get());
            } else {
                tableOrders.getItems().clear();
            }
        }
    }

    public void selectedDate() {
        if (datePicker.getValue() != null) {
            tableOrders.getItems().clear();
            if (orderService.getOrdersByDate(datePicker.getValue()).isRight()) {
                tableOrders.getItems().addAll(orderService.getOrdersByDate(datePicker.getValue()).get());
            } else {
                tableOrders.getItems().clear();
            }
        }
    }

    public void selectedFilter() {
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(orderService.getAll().get());

        if (filterComboBox.getValue() == null) {
            datePicker.setDisable(true);
            comboBoxCustomer.setDisable(true);

        } else if (filterComboBox.getValue().equals("Date")) {
            datePicker.setDisable(false);
            comboBoxCustomer.setDisable(true);

        } else if (filterComboBox.getValue().equals("Customer")) {
            comboBoxCustomer.setDisable(false);
            datePicker.setDisable(true);

        } else {
            comboBoxCustomer.setDisable(true);
            datePicker.setDisable(true);
        }
    }
}


