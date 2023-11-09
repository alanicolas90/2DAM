package dao;

import dao.model.Customer;
import dao.model.ErrorC;
import io.vavr.control.Either;

import java.util.List;

public interface CustomerDao {

    Either<ErrorC, List<Customer>> getAll();

    Either<ErrorC, Integer> saveAutoIncrementalID(Customer customer);

    Either<ErrorC, Customer> get(int id);

    Either<ErrorC, Integer> update(Customer customerUpdated);

    Either<ErrorC, Integer> delete(int id, boolean confirm);
}
