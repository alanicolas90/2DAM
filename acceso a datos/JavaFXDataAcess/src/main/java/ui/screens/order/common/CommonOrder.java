package ui.screens.order.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderItem;
import ui.screens.common.ConstantNormal;

import java.time.LocalDateTime;

public class CommonOrder {

    public void initOrderList(TableColumn<Order, Integer> columnId, TableColumn<Order, LocalDateTime> columnDate, TableColumn<Order, Integer> columnCustomerId, TableColumn<Order, Integer> columnTableNumber) {
        columnId.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.ID));
        columnDate.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.DATE));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.CUSTOMER_ID));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.TABLE_NUMBER));

    }

    public void initOrderItemList(TableColumn<OrderItem, String> columnItemName, TableColumn<OrderItem, Integer> columnQuantity) {
        columnItemName.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.ITEM_NAME));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.QUANTITY));
        }


    private CommonOrder() {
    }
}
