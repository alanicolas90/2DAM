package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
    int id;
    int orderId;
    int menuItemId;
    int quantity;

}
