package dao;

import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    Either<ErrorC, List<Customer>> getAll();
}
