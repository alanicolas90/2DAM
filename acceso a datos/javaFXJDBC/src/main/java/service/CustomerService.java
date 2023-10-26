package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.scene.control.TextField;
import model.Customer;
import model.ErrorC;
import service.utils.ServiceConstants;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao;

    @Inject
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Either<ErrorC, List<Customer>> getAll() {
        return customerDao.getAll();
    }

    public Either<ErrorC, Integer> saveAutoIncrementalID(Customer customer) {
        return customerDao.saveAutoIncrementalID(customer);
    }

    public Either<ErrorC, Integer> delete(int id, boolean confirm) {
        return customerDao.delete(id, confirm);
    }


    public Either<ErrorC, Integer> update(Customer customerUpdated) {
        return customerDao.update(customerUpdated);
    }

    public Either<ErrorC, Customer> getCustomerById(int id) {
        return customerDao.get(id);
    }

    public List<Integer> getAllIdsCustomer() {
        List<Customer> customers = customerDao.getAll().get();
        return customers.stream().map(Customer::getId).toList();

    }
}
