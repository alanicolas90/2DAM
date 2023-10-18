package ui.screens.order;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Order;
import ui.screens.common.BaseScreenController;
import ui.screens.common.ConstantNormal;
import ui.screens.order.common.CommonOrder;

import java.time.LocalDateTime;

public class OrderUpdateController extends BaseScreenController {

    private final CommonOrder common;

    @Inject
    public OrderUpdateController(CommonOrder common) {
        this.common = common;
    }

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
    }

    @Override
    public void principalCargado() {
        //tableOrders.getItems().addAll(orderService.getAll().get());
    }


    public void selectionTable() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        dateOfBirthCustomer.setValue(order.getDate().toLocalDate());
        txtCustomerId.setText(String.valueOf(order.getCustomerId()));
        txtTableNumber.setText(String.valueOf(order.getTableNumber()));
    }

    public void updateOrder() {
        Order order = tableOrders.getSelectionModel().getSelectedItem();
        if (order == null) {
            getPrincipalController().alertWarning(ConstantNormal.YOU_MUST_SELECT_AN_ORDER, ConstantNormal.ERROR);
        } else if (txtCustomerId.getText().isEmpty() || txtTableNumber.getText().isEmpty() || dateOfBirthCustomer.getValue().toString().isBlank()) {
            getPrincipalController().alertWarning(ConstantNormal.THERE_ARE_EMPTY_FIELDS, ConstantNormal.ERROR);
        } else if (!txtCustomerId.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.CUSTOMER_ID_MUST_BE_A_NUMBER, ConstantNormal.ERROR);
        } else if (!txtTableNumber.getText().matches(ConstantNormal.CONTAINS_NUMBERS)) {
            getPrincipalController().alertWarning(ConstantNormal.TABLE_NUMBER_MUST_BE_A_NUMBER, ConstantNormal.ERROR);
        }
//        else if (customerService.get(Integer.parseInt(txtCustomerId.getText())).isLeft()) {
//            getPrincipalController().alertWarning(ConstantNormal.CUSTOMER_NOT_FOUND, ConstantNormal.ERROR);
//        }
        else {
            Order newOrder = new Order(order.getId(), order.getDate(), Integer.parseInt(txtCustomerId.getText()), Integer.parseInt(txtTableNumber.getText()));
            // orderService.update(newOrder);
            tableOrders.getItems().clear();
            //tableOrders.getItems().addAll(orderService.getAll().get());
            getPrincipalController().showInformation(ConstantNormal.ORDER_UPDATED_SUCCESSFULLY, ConstantNormal.INFORMATION);
        }
    }
}
