package ui.screens.order.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuItemTable {
    private Integer order_id;
    private String name;
    private Double price;
    private Integer quantity;
    private Double total;

}
