package dao;

import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.util.List;

public interface CustomerDao {

    Either<ErrorC, List<Customer>> getAll();

    Either<ErrorC, Integer> save(Customer customer);

    Either<ErrorC, Customer> get(int id);

    Either<ErrorC, Integer> update(Customer customerUpdated);

    Either<ErrorC, Integer> delete(int id, boolean confirm);
}
