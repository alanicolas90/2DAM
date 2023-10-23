package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    int id;
    String name;
    String surname;
    String email;
    int phone;
    LocalDate birthDate;


    public Customer(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Customer(String name, String surname, String email, int phone, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public Customer(int id, String name, String surname, String email, int phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String toStringTextFile() {
        if (phone == 0) {
            return id + ";" + name + ";" + surname + ";" + email + ";" + ";" + birthDate;
        } else {
            return id + ";" + name + ";" + surname + ";" + email + ";" + phone + ";" + birthDate;
        }
    }

    public Customer(String line) {
        String[] customerData = line.split(";");
        if (!customerData[0].equals("id")) {
            this.id = Integer.parseInt(customerData[0]);
            this.name = customerData[1];
            this.surname = customerData[2];
            this.email = customerData[3];
            if (customerData[4].isEmpty()) {
                this.phone = 0;
            } else {
                this.phone = Integer.parseInt(customerData[4]);
            }
            this.birthDate = LocalDate.parse(customerData[5]);
        }
    }
}
