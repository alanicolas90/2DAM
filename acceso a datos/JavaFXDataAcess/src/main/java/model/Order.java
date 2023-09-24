package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Order {
    int id;
    LocalDateTime date;
    int customerId;
    int tableNumber;

    public Order(LocalDateTime date, int customerId, int tableNumber) {
        this.date = date;
        this.customerId = customerId;
        this.tableNumber = tableNumber;
    }

    public Order() {

    }

}
