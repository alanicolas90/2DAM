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

    public Order(int id, int customerId, int tableNumber) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.customerId = customerId;
        this.tableNumber = tableNumber;
    }

    public Order() {

    }

    public Order(String line) {
        if (!line.isBlank()) {
            String[] orderData = line.split(";");
            if (orderData[0].isBlank()) {
                this.id =0;
            } else {
                this.id = Integer.parseInt(orderData[0]);
            }
            this.date = LocalDateTime.parse(orderData[1].replace(" ", "T"));
            this.customerId = Integer.parseInt(orderData[2]);
            this.tableNumber = Integer.parseInt(orderData[3]);

        }else{
            this.id = Integer.parseInt(null);
            this.date = LocalDateTime.parse(null);
            this.customerId = Integer.parseInt(null);
            this.tableNumber = Integer.parseInt(null);
        }
    }

    public String toStringTextFile() {
        return id + ";" + date.toString().replace("T"," ") + ";" + customerId + ";" + tableNumber;
    }

}
