package ui.screens.customer.common;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import ui.screens.common.ConstantNormal;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerCommon {

    public void initCustomerTable(TableColumn<Customer, Integer> columnId, TableColumn<Customer, String> columnName, TableColumn<Customer, String> columnSurname, TableColumn<Customer, String> columnEmail, TableColumn<Customer, Integer> columnPhone, TableColumn<Customer, LocalDate> columnDateBirth) {
        columnId.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.ID));
        columnName.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.NAME));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.SURNAME));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.EMAIL));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.PHONE));
        columnDateBirth.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.BIRTH_DATE));
    }

    public void initOrderTable(TableColumn<Customer, Integer> columnIdOrder, TableColumn<Customer, LocalDateTime> columnDate, TableColumn<Customer, Integer> columnCustomerIdOrder, TableColumn<Customer, Integer> columnTableNumber) {
        columnIdOrder.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.ID));
        columnDate.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.DATE));
        columnCustomerIdOrder.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.CUSTOMER_ID));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>(ConstantNormal.TABLE_NUMBER));
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
