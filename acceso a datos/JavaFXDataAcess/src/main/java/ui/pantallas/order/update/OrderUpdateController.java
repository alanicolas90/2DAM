package ui.pantallas.order.update;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import service.OrderService;
import ui.pantallas.common.BaseScreenController;
;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class OrderUpdateController extends BaseScreenController {

    @Inject
    private OrderService orderService;
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
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
    }

    @Override
    public void principalCargado() {
        tableOrders.getItems().addAll(orderService.getAll().get());
    }


    public void selectionTable() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        dateOfBirthCustomer.setValue(order.getDate().toLocalDate());
        txtCustomerId.setText(String.valueOf(order.getCustomerId()));
        txtTableNumber.setText(String.valueOf(order.getTableNumber()));
    }
}
