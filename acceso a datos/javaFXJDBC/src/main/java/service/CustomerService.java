package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
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

    public int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth){
        return customerDao.saveAutoIncrementalID(name, surname, email, phone, dateOfBirth);
    }

    public int delete(int id){
        return customerDao.delete(id);
    }
}
