package ui.screens.order.add;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import service.CustomerService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderAddController extends BaseScreenController {

    @Inject
    private OrderService orderService;
    @Inject
    private CustomerService customerService;
    @Inject
    private CommonOrder common;

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
    private DatePicker dateOrder;

    public void initialize() {
        common.initOrderList(columnId, columnDate, columnCustomerId, columnTableNumber);
        comboBoxCustomer.getItems().addAll(customerService.getAllIds().get());
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }

    public void addOrder() {
        Order order = new Order();
        int id = tableOrders.getItems().size() - 1;
        if (dateOrder.getValue() == null || txtTableNumber.getText().isEmpty() || comboBoxCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        } else if (!txtTableNumber.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.TABLE_NUMBER_CANNOT_CONTAIN_LETTERS, ConstantNormal.ERROR);
        } else {
            order.setId(id + 2);
            order.setDate(dateOrder.getValue().atTime(0, 0, 0));
            order.setCustomerId(comboBoxCustomer.getValue());
            order.setTableNumber(Integer.parseInt(txtTableNumber.getText()));
            tableOrders.getItems().add(order);
            getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
        }
    }
}
