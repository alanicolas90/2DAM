package dao;

import dao.model.Customer;
import dao.model.ErrorC;
import io.vavr.control.Either;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAll();

    Customer saveAutoIncrementalID(Customer customer);

    Either<ErrorC, Customer> get(int id);

    Either<ErrorC, Integer> update(Customer customerUpdated);

    Integer delete(int id);
}
