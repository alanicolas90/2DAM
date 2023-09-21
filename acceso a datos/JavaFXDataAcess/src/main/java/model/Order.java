package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Timestamp;

@Data
@AllArgsConstructor
public class Order {
    int id;
    Timestamp date;
    int customerId;
    int tableNumber;

    public Order(Timestamp date, int customerId, int tableNumber) {
        this.date = date;
        this.customerId = customerId;
        this.tableNumber = tableNumber;
    }

}
