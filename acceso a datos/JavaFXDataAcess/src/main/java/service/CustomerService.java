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
    public Either<ErrorC, List<Integer>> getAllIds() {
        return dao.getAllIds();
    }

    public Either<ErrorC, Customer> get(int id) {
        return dao.get(id);
    }

}
