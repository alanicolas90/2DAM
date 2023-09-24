package ui.pantallas.order.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.OrderItem;

import java.time.LocalDateTime;

public class CommonOrder {

    public void initOrderList(TableColumn<Order, Integer> columnId, TableColumn<Order, LocalDateTime> columnDate, TableColumn<Order, Integer> columnCustomerId, TableColumn<Order, Integer> columnTableNumber){
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));

    }

    public void initOrderItemList(TableColumn<OrderItem, String> columnItemName, TableColumn<OrderItem, Integer> columnQuantity, TableColumn<OrderItem, Integer> columnPrice, TableColumn<Integer, Integer> columnTotalPrice){
        columnItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }



    private CommonOrder() {
    }
}
