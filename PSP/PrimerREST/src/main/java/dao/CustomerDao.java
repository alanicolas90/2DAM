package dao;

import dao.model.Customer;
import dao.model.ErrorC;
import io.vavr.control.Either;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAll();

    Integer save(Customer customer);

    Customer get(int id);

    Integer update(Customer customerUpdated);

    Integer delete(int id);
}
