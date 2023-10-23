package ui.screens.customer.common;


import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import ui.screens.common.ConstantsController;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerCommon {

    public void initCustomerTable(TableColumn<Customer, Integer> columnId, TableColumn<Customer, String> columnName, TableColumn<Customer, String> columnSurname, TableColumn<Customer, String> columnEmail, TableColumn<Customer, Integer> columnPhone, TableColumn<Customer, LocalDate> columnDateBirth) {
        columnId.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ID));
        columnName.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.NAME));
        columnSurname.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.SURNAME));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.EMAIL));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.PHONE));
        columnDateBirth.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.BIRTH_DATE));
    }

    public void initOrderTable(TableColumn<Customer, Integer> columnIdOrder, TableColumn<Customer, LocalDateTime> columnDate, TableColumn<Customer, Integer> columnCustomerIdOrder, TableColumn<Customer, Integer> columnTableNumber) {
        columnIdOrder.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.ID));
        columnDate.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.DATE));
        columnCustomerIdOrder.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.CUSTOMER_ID));
        columnTableNumber.setCellValueFactory(new PropertyValueFactory<>(ConstantsController.TABLE_NUMBER));
    }

    private CustomerCommon() {
    }
}
