package service;

import dao.CustomerDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.ErrorC;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public int delete(int id) {
        return customerDao.delete(id);
    }

    public Integer update(Customer customerOld, Customer customerUpdated) {
        int changes = 0;
        int customerUpdatedId = customerUpdated.getId();

        if (!Objects.equals(customerOld.getName(), customerUpdated.getName())) {
            customerDao.updateName(customerUpdatedId, customerUpdated.getName());
            changes++;
        }
        if (!Objects.equals(customerOld.getSurname(), customerUpdated.getSurname())) {
            customerDao.updateSurname(customerUpdatedId, customerUpdated.getSurname());
            changes++;
        }
        if (customerOld.getPhone() != customerUpdated.getPhone()) {
            customerDao.updatePhone(customerUpdatedId, customerUpdated.getPhone());
            changes++;
        }
        if (!Objects.equals(customerOld.getEmail(), customerUpdated.getEmail())) {
            customerDao.updateEmail(customerUpdatedId, customerUpdated.getEmail());
            changes++;
        }
        if (!Objects.equals(customerOld.getBirthDate(), customerUpdated.getBirthDate())) {
            customerDao.updateDateOfBirth(customerUpdatedId, customerUpdated.getBirthDate());
            changes++;
        }
        return changes;

    }

    public Customer getCustomerById(int id) {
        return customerDao.getCustomerById(id).get();
    }

//    public boolean update(Customer customerOld, Customer customerUpdated) {
//        return customerDao.update(customerUpdated);
//    }
}
