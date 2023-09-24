package ui.pantallas.customer.common;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerCommon {
    public void initCustomerTable(TableColumn<Customer, Integer> columnId, TableColumn<Customer, String> columnName, TableColumn<Customer, String> columnSurname, TableColumn<Customer, String> columnEmail, TableColumn<Customer, Integer> columnPhone, TableColumn<Customer, LocalDate> columnDateBirth) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnDateBirth.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    public void initOrderTable(TableColumn<Customer, Integer> columnIdOrder, TableColumn<Customer, LocalDateTime> columnDate, TableColumn<Customer, Integer> columnCustomerIdOrder, TableColumn<Customer, Integer> columnTableNumber) {
        columnIdOrder.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnCustomerIdOrder.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
    }

    public void setsNameSurnameEmailPhoneBirth(Customer customer, TextField txtName, TextField txtSurname, TextField txtEmail, TextField txtPhoneNumber, DatePicker dateOfBirthCustomer) {
        customer.setName(txtName.getText());
        customer.setSurname(txtSurname.getText());
        customer.setEmail(txtEmail.getText());
        customer.setPhone(Integer.parseInt(txtPhoneNumber.getText()));
        if (dateOfBirthCustomer.getValue() != null) {
            customer.setBirthDate(dateOfBirthCustomer.getValue());
        }
    }
    private CustomerCommon() {
    }
}
