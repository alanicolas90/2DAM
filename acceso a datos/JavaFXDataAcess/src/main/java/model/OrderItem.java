package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    int id;
    int orderId;
    int menuItemId;
    int quantity;
}
