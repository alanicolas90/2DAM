package ui.screens.order.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderItem;
import ui.screens.common.ConstantsController;

import java.time.LocalDateTime;

public class CommonOrder {

    public void initOrderList(TableColumn<Order, Integer> columnId, TableColumn<Order, LocalDateTime> columnDate, TableColumn<Order, Integer> columnCustomerId, TableColumn<Order, Integer> columnTableNumber) {
        columnId.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ID));
        columnDate.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.DATE));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.CUSTOMER_ID));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.TABLE_NUMBER));

    }

    public void initOrderItemList(TableColumn<OrderItem, String> columnItemName, TableColumn<OrderItem, Integer> columnQuantity) {
        columnItemName.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ITEM_NAME));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.QUANTITY));
    }


    private CommonOrder() {
    }
}
