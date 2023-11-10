package dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    int id;
    String itemName;
    int quantity;
    int customerId;

    public Order(String itemName, int quantity, int customerId) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.customerId = customerId;
    }
}
