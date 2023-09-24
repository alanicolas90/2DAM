package dao.impl;

import dao.CustomerDao;
import io.vavr.control.Either;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Either<ErrorC, List<Customer>> getAll() {

        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Juan", "Perez", "juanperez@gmail.com"));
        customerList.add(new Customer(2, "Pedro", "Gomez", "pedrogomez@gmail.com", 123456789, LocalDate.of(1990, 1, 1)));
        customerList.add(new Customer(3, "Maria", "Lopez", "marialopez@gmail.com", 987654321, LocalDate.of(1995, 1, 1)));


        return Either.right(customerList);
    }

    @Override
    public Either<ErrorC, List<Integer>> getAllIds() {
        List<Customer> customerList = getAll().get();

        return Either.right(customerList.stream().map(Customer::getId).toList());
    }

    @Override
    public Either<ErrorC, Customer> get(int id) {

        List<Customer> customerList = getAll().get();

        return Either.right(customerList.stream().filter(c -> c.getId() == id).findFirst().stream().toList().get(0));
    }
}
