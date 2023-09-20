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
}
