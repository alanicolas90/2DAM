package dao;

import io.vavr.control.Either;
import model.Customer;

import java.util.List;

public interface CustomerDao {
    Either<Integer, List<Customer>> getAll();

    Either<Integer, Customer> get(int id);
}
