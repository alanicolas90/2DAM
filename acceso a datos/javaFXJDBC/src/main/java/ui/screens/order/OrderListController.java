package ui.screens.order;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.ErrorC;
import model.Order;
import model.OrderItem;
import service.CustomerService;
import service.OrdersService;
import ui.screens.common.BaseScreenController;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;
import java.util.List;

public class OrderListController extends BaseScreenController {

    private final CommonOrder commonOrder;
    private final OrdersService ordersService;
    private final CustomerService customerService;

    @Inject
    public OrderListController(CommonOrder commonOrder, OrdersService ordersService, CustomerService customerService) {
        this.commonOrder = commonOrder;
        this.ordersService = ordersService;
        this.customerService = customerService;
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
    private TableView tableOrderItems;
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

        comboBoxCustomer.getItems().addAll(customerService.getAllIdsCustomer());
        filterComboBox.getItems().addAll("Date", "Customer", "None");
        comboBoxCustomer.setDisable(true);
        datePicker.setDisable(true);

    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(ordersService.getAll().get());
        filterComboBox.setOnAction(event -> selectedFilter());
        comboBoxCustomer.setOnAction(event -> selectedBox());
        datePicker.setOnAction(event -> selectedDate());
    }

    public void orderSelected() {
        tableOrderItems.getItems().clear();
        if (tableOrders.getSelectionModel().getSelectedItem() != null) {
            int idCustomer = tableOrders.getSelectionModel().getSelectedItem().getCustomerId();
            if(customerService.getCustomerById(idCustomer).isRight()){
                txtCustomerName.setText(customerService.getCustomerById(idCustomer).get().getName());
            }else{
                getPrincipalController().showInformation("Customer has no orders","Information");
            }
        }
    }

    public void selectedBox() {
        if (comboBoxCustomer.getValue() != null) {
            tableOrders.getItems().clear();
            Either<ErrorC, List<Order>> eitherOrders = ordersService.getOrdersSpecificCustomer(comboBoxCustomer.getValue());
            if(eitherOrders.isRight()) {
                tableOrders.getItems().addAll(eitherOrders.get());
            }
        }
    }

    public void selectedDate() {
        if (datePicker.getValue() != null) {
            tableOrders.getItems().clear();
            Either<ErrorC, List<Order>> eitherOrders = ordersService.getOrdersSpecificDate(datePicker.getValue());
            if(eitherOrders.isRight()){
                tableOrders.getItems().addAll(eitherOrders.get());
            }
        }
    }

    public void selectedFilter() {
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(ordersService.getAll().get());

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


