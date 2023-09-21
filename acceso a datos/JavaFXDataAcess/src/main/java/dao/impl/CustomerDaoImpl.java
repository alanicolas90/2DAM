package dao.impl;

import dao.CustomerDao;
import io.vavr.control.Either;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Either<Integer, List<Customer>> getAll() {
        Either <Integer, List<Customer>> result;

        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer(1, "Juan", "Perez", "juanperez@gmail.com"));
        customerList.add(new Customer(2, "Pedro", "Gomez", "pedrogomez@gmail.com", 123456789));
        customerList.add(new Customer(3, "Maria", "Lopez", "marialopez@gmail.com", 987654321));

        result = Either.right(customerList);
        return result;
    }

    @Override
    public Either<Integer, Customer> get(int id) {
        return null;
    }
}
