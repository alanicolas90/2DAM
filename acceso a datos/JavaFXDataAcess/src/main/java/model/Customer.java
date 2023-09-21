package model;

import lombok.Data;

@Data
public class Customer {
    int id;
    String name;
    String surname;
    String email;
    String phone;

    public Customer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
