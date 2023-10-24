package dao;

import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {

    Either<ErrorC, List<Customer>> getAll();

    int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth);

    Either<ErrorC, Customer> get(int id);

    Either<ErrorC, Integer> update(Customer customerUpdated);

    Either<ErrorC, Integer> delete(int id, boolean confirm);
}