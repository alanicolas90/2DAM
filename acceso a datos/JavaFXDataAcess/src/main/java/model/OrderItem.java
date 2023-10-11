package model;

import lombok.Data;

@Data
public class OrderItem {
    int id;
    int orderId;
    int menuItemId;
    int quantity;


    public OrderItem() {
    }
}
