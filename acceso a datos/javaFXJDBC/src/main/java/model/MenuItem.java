package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    int id;
    String itemName;
    String description;
    double price;

    public MenuItem(String itemName) {
        this.itemName = itemName;
    }

}
