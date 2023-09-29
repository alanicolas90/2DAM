package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
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

    public Order parseToClass(String s) {
        Order o = new Order();
        if (!s.isBlank()) {
            String[] orderData = s.split(";");
            if (orderData[0].isBlank()) {
                o.setId(0);
            } else {
                o.setId(Integer.parseInt(orderData[0]));
            }
            o.setDate(LocalDateTime.parse(orderData[1].replace(" ", "T")));
            o.setCustomerId(Integer.parseInt(orderData[2]));
            o.setTableNumber(Integer.parseInt(orderData[3]));
            return o;
        }else{
            return null;
        }
    }

    public String toStringTextFile() {
        return id + ";" + date.toString().replace("T"," ") + ";" + customerId + ";" + tableNumber;
    }

}
