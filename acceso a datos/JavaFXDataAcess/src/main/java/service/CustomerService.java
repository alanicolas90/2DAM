package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.util.List;

public class CustomerService {

    @Inject
    private CustomerDao dao;

    public Either<ErrorC, List<Customer>> getAll() {
        return dao.getAll();
    }

    public Either<Integer, Customer> get(int id) {
        return dao.get(id);
    }

    public int save(Customer t) {
        return 0;
    }

    public int update(Customer t) {
        return 0;
    }

    public int delete(Customer t) {
        return 0;
    }

}
