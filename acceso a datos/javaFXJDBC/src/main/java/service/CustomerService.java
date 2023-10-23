package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao;

    @Inject
    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Either<ErrorC, List<Customer>> getAll() {
        return customerDao.getAll();
    }

    public int saveAutoIncrementalID(String name, String surname, String email, int phone, LocalDate dateOfBirth) {
        return customerDao.saveAutoIncrementalID(name, surname, email, phone, dateOfBirth);
    }

    public Either<ErrorC, Integer> delete(int id, boolean confirm){
        if(customerDao.delete(id,confirm).isRight()){
            return Either.right(1);
        }else{
            return Either.left(new ErrorC("Are you sure you want to delete this customer?"));
        }
    }



    public Either<ErrorC,Integer> update(Customer customerUpdated) {
        if(customerDao.update(customerUpdated).isLeft() ){
            return Either.left(new ErrorC("Customer not found"));
        } else{
            return Either.right(customerDao.update(customerUpdated).get());
        }
    }

    public Either<ErrorC,Customer> getCustomerById(int id) {
        if(customerDao.get(id).isLeft()){
            return Either.left(new ErrorC("Customer not found"));
        }else{
            return Either.right(customerDao.get(id).get());
        }
    }

    public List<Integer> getAllIdsCustomer() {
        List<Customer> customers = customerDao.getAll().get();
        if(customers.isEmpty()){
            return Collections.emptyList();
        }else{
            return customers.stream().map(Customer::getId).toList();
        }
    }
}
