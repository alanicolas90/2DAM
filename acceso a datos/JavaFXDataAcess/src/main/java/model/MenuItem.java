package model;

import lombok.Data;

@Data
public class MenuItem {
    int id;
    String itemName;
    String description;
    double price;

    public MenuItem(String itemName) {
        this.itemName = itemName;
    }

    public MenuItem() {
    }
}
