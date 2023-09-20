package model;

import lombok.*;

@Data
@AllArgsConstructor
public class Coffee {
    String name;
    float price;
    int sales;
    int total;

}