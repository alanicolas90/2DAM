package ui.pantallas.customer.common;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.time.LocalDate;

public class CustomerCommon {
    public void initCustomerTable(TableColumn<Customer, Integer> columnId, TableColumn<Customer, String> columnName, TableColumn<Customer, String> columnSurname, TableColumn<Customer, String> columnEmail, TableColumn<Customer, Integer> columnPhone, TableColumn<Customer, LocalDate> columnDateBirth) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnDateBirth.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    private CustomerCommon() {
    }
}
