package dao;

import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {

    Either<ErrorC, List<Customer>> getAll();
    int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth);

    int delete(int id);

    Either<ErrorC, Customer> getCustomerById(int id);

//    boolean update(Customer customerUpdated);

    void updateName(int id, String name);

    void updateSurname(int id, String surname);

    void updatePhone(int id, int phone);

    void updateEmail(int id, String email);

    void updateDateOfBirth(int id, LocalDate dateOfBirth);
}
