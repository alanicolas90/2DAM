package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.MenuItem;
import model.Order;
import service.CustomerService;
import service.OrderService;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class OrderAddController extends BaseScreenController {

    @Inject
    public OrderAddController(OrderService orderService, CustomerService customerService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    private final OrderService orderService;
    private final CustomerService customerService;

    @FXML
    private ComboBox<Integer> comboBoxCustomer;
    @FXML
    private TextField txtTableNumber;
    @FXML
    private DatePicker dateOrder;

    @FXML
    private ComboBox<MenuItem> comboBoxMenuItem;

    @FXML
    private TableView<MenuItem> tableOrderItems;
    @FXML
    private TableColumn<MenuItem, String> columnItemName;
    @FXML
    private TableColumn<MenuItem, Integer> columnItemId;
    @FXML
    private TableColumn<MenuItem, Double> columnItemPrice;
    @FXML
    private TableColumn<MenuItem, String> columnItemDescription;

    public void initialize() {
        comboBoxCustomer.getItems().addAll(customerService.getAllIds().get());
    }


    public void addOrderItem() {
        if (dateOrder.getValue() == null || txtTableNumber.getText().isEmpty() || comboBoxCustomer.getValue() == null) {
            getPrincipalController().alertWarning(ConstantNormal.ALL_THE_FIELDS_MUST_BE_FILLED, ConstantNormal.ERROR);
        } else {
            int orderId = orderService.getAll().get().stream().mapToInt(Order::getId).max().orElse(0) + 1;
            Order order = new Order(orderId, LocalDateTime.of(dateOrder.getValue(), LocalTime.now()), comboBoxCustomer.getValue(), Integer.parseInt(txtTableNumber.getText()));

            if (orderService.save(order).isRight()) {
                getPrincipalController().showInformation(ConstantNormal.ORDER_ADDED_SUCCESSFULLY, ConstantNormal.SUCCESS);
            } else {
                getPrincipalController().alertWarning(ConstantNormal.ERROR_ADDING_ORDER, ConstantNormal.ERROR);
            }
        }
    }


}
