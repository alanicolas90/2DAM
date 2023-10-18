package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao;

    @Inject
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Either<ErrorC,List<Customer>> getAll(){
        return customerDao.getAll();
    }

}
