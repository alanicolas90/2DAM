package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
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
            return id + ";" + name + ";" + surname + ";" + email + ";" +  ";" + birthDate;
        } else {
            return id + ";" + name + ";" + surname + ";" + email + ";" + phone + ";" + birthDate;
        }
    }

    public Customer parseToClass(String s) {
        String[] customerData = s.split(";");
        Customer c = new Customer();
        c.setId(Integer.parseInt(customerData[0]));
        c.setName(customerData[1]);
        c.setSurname(customerData[2]);
        c.setEmail(customerData[3]);
        if (customerData[4].isEmpty()) {
            c.setPhone(0);
        } else {
            c.setPhone(Integer.parseInt(customerData[4]));
        }
        c.setBirthDate(LocalDate.parse(customerData[5]));

        return c;
    }


    public Customer() {

    }
}
